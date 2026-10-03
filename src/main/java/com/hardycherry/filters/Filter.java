package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

import java.util.ArrayList;
import java.util.List;

public class Filter<R, L, K, V> {

    protected final List<FilterOperation> operations;

    private Filter(List<FilterOperation> operations) {
        this.operations = operations;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder<R, L, K, V> {
        private final List<FilterOperation> builderOperations;

        public Builder() {
            builderOperations = new ArrayList<>();
        }

        public Builder<R, L, K, V> and(Filter<R, L, K, V> rightHandSide, Filter<R, L, K, V> leftHandSide) {
            builderOperations.add(new FilterOperationAnd<>(rightHandSide, leftHandSide));
            return this;
        }

        public Builder<R, L, K, V> or(Filter<R, L, String, V> rightHandSide, Filter<R, L, String, V> leftHandSide) {
            builderOperations.add(new FilterOperationOr<>(rightHandSide, leftHandSide));
            return this;
        }

        public Builder<R, L, K, V> not(Filter<R, L, K, V> filter) {
            builderOperations.add(new FilterOperationNot<>(filter));
            return this;
        }

        public Builder<R, L, K, V> gt(String key, String value) {
            builderOperations.add(new FilterOperationGreaterThan<V>(key, value));
            return this;
        }

        public Builder<R, L, K, V> lt(String key, String value) {
            builderOperations.add(new FilterOperationLessThan<V>(key, value));
            return this;
        }

        public Builder<R, L, K, V> isPresent(String key) {
            builderOperations.add(new FilterOperationIsPresent<V>(key));
            return this;
        }

        public Builder<R, L, K, V> equals(String key, L value) {
            builderOperations.add(new FilterOperationEquals<L, V>(key, value));
            return this;
        }

        public Builder<R, L, K, V> isFalse(String key) {
            builderOperations.add(new FilterOperationFalse<V>(key));
            return this;
        }

        public Builder<R, L, K, V> isTrue(String key) {
            builderOperations.add(new FilterOperationTrue<V>(key));
            return this;
        }

        public Builder<R, L, K, V> regex(String key, String regex) {
            builderOperations.add(new FilterOperationRegex<V>(key, regex));
            return this;
        }

        public Filter build() {
            return new Filter(builderOperations);
        }
    }

    public boolean matches(ResourceDto<K, V> resourceDto) throws FilterOperationException {
        boolean result = true;

        for(FilterOperation operation : operations) {
            if(!operation.evaluateResource(resourceDto)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public List<ResourceDto<K, V>> filterResources(List<ResourceDto<K, V>> resources) throws FilterOperationException {
        List<ResourceDto<K, V>> results = new ArrayList<>();
        if(resources == null) {
            return results;
        }

        for(ResourceDto<K, V> resource : resources) {
            if(matches(resource)) {
                results.add(resource);
            }
        }
        return results;
    }
}
