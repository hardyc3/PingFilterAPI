package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    protected final List<FilterOperation<?, ?>> operations;

    private Filter(List<FilterOperation<?, ?>> operations) {
        this.operations = operations;
    }


    public static class Builder {
        protected List<FilterOperation<?, ?>> builderOperations;

        public Builder() {
            builderOperations = new ArrayList<>();
        }

        public Builder and(Filter rightHandSide, Filter leftHandSide) {
            builderOperations.add(new FilterOperationAnd(rightHandSide, leftHandSide));
            return this;
        }

        public Builder or(Filter rightHandSide, Filter leftHandSide) {
            builderOperations.add(new FilterOperationOr(rightHandSide, leftHandSide));
            return this;
        }

        public Builder not(Filter filter) {
            builderOperations.add(new FilterOperationNot(filter));
            return this;
        }

        public Builder gt(String key, String value) {
            builderOperations.add(new FilterOperationGreaterThan(key, value));
            return this;
        }

        public Builder lt(String key, String value) {
            builderOperations.add(new FilterOperationLessThan(key, value));
            return this;
        }

        public Builder isPresent(String key) {
            builderOperations.add(new FilterOperationIsPresent(key));
            return this;
        }

        public Builder equals(String key, String value) {
            builderOperations.add(new FilterOperationEquals(key, value));
            return this;
        }

        public Builder isFalse(String key) {
            builderOperations.add(new FilterOperationFalse(key));
            return this;
        }

        public Builder isTrue(String key) {
            builderOperations.add(new FilterOperationTrue(key));
            return this;
        }

        public Builder regex(String key, String regex) {
            builderOperations.add(new FilterOperationRegex(key, regex));
            return this;
        }

        public Filter build() {
            return new Filter(builderOperations);
        }
    }

    public boolean matches(ResourceDto resourceDto) throws FilterOperationException {
        boolean result = true;

        for(FilterOperation<?, ?> operation : operations) {
            if(!operation.evaluateResource(resourceDto)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public List<ResourceDto> filterResources(List<ResourceDto> resources) throws FilterOperationException {
        List<ResourceDto> results = new ArrayList<>();
        if(resources == null) {
            return results;
        }

        for(ResourceDto resource : resources) {
            if(matches(resource)) {
                results.add(resource);
            }
        }
        return results;
    }
}
