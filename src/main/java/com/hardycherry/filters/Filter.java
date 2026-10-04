package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

import java.util.ArrayList;
import java.util.List;

public class Filter<K, V> {

    private final List<FilterOperation<K, V>> operations;

    private Filter(List<FilterOperation<K, V>> operations) {
        this.operations = operations;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static class Builder<K, V> {
        private final List<FilterOperation<K, V>> builderOperations;

        public Builder() {
            builderOperations = new ArrayList<>();
        }

        public Builder<K, V> and(Filter<K, V> filters) {
            builderOperations.add(new FilterOperationAnd<>(filters));
            return this;
        }

        public Builder<K, V> or(Filter<K, V> filters) {
            builderOperations.add(new FilterOperationOr<>(filters));
            return this;
        }

        public Builder<K, V> not(Filter<K, V> filters) {
            builderOperations.add(new FilterOperationNot<>(filters));
            return this;
        }

        public Builder<K, V> gt(K key, K otherKey) {
            builderOperations.add(new FilterOperationGreaterThan<>(key, otherKey));
            return this;
        }

        public Builder<K, V> lt(K key, K otherKey) {
            builderOperations.add(new FilterOperationLessThan<>(key, otherKey));
            return this;
        }

        public Builder<K, V> isPresent(K key) {
            builderOperations.add(new FilterOperationIsPresent<>(key));
            return this;
        }

        public Builder<K, V> equals(K key, V value) {
            builderOperations.add(new FilterOperationEquals<>(key, value));
            return this;
        }

        public Builder<K, V> isFalse(K key) {
            builderOperations.add(new FilterOperationFalse<>(key));
            return this;
        }

        public Builder<K, V> isTrue(K key) {
            builderOperations.add(new FilterOperationTrue<>(key));
            return this;
        }

        public Builder<K, V> regex(K key, String regex) {
            builderOperations.add(new FilterOperationRegex<>(key, regex));
            return this;
        }

        public Filter<K, V> build() {
            return new Filter<>(builderOperations);
        }
    }

    public boolean allMatch(ResourceDto<K, V> resourceDto) throws FilterOperationException {
        boolean result = true;

        for(FilterOperation<K, V> operation : operations) {
            if(!operation.evaluateResource(resourceDto)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean anyMatch(ResourceDto<K, V> resourceDto) throws FilterOperationException {
        boolean result = false;

        for(FilterOperation<K, V> operation : operations) {
            if(operation.evaluateResource(resourceDto)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public List<ResourceDto<K, V>> filterResources(List<? extends ResourceDto<K, V>> resources) throws FilterOperationException {
        List<ResourceDto<K, V>> results = new ArrayList<>();
        if(resources == null) {
            return results;
        }

        for(ResourceDto<K, V> resource : resources) {
            if(allMatch(resource)) {
                results.add(resource);
            }
        }
        return results;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(var operation : operations) {
            builder.append(operation.toString())
                    .append(System.lineSeparator());
        }

        return builder.substring(0, builder.length()-System.lineSeparator().length()).toString();
    }
}
