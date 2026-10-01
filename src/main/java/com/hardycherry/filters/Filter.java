package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    private List<FilterOperation> operations;

    private Filter(List<FilterOperation> operations) {
        this.operations = operations;
    }


    public static class Builder {
        List<FilterOperation> builderOperations;

        public Builder() {
            builderOperations = new ArrayList<>();
        }

        public Builder and() {

            return this;
        }

        public Builder or() {

            return this;
        }

        public Builder not() {

            return this;
        }

        public Builder gt(String key, String value) {

            return this;
        }

        public Builder lt(String key, String value) {

            return this;
        }

        public Builder isPresent(String key) {

            return this;
        }

        public Builder equals(String key, String value) {

            return this;
        }

        public Builder isFalse(String key) {

            return this;
        }

        public Builder isTrue(String key) {

            return this;
        }

        public Builder regex(String key, String regex) {

            return this;
        }

        public Filter build() {
            return new Filter(builderOperations);
        }
    }

    public boolean matches(ResourceDto resourceDto) {
        boolean result = true;

        for(FilterOperation operation : operations) {
            if(!operation.evaluateResource(resourceDto)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public List<ResourceDto> filterResources(List<ResourceDto> resources) {
        List<ResourceDto> results = new ArrayList<>();
        for(ResourceDto resource : resources) {
            if(matches(resource)) {
                results.add(resource);
            }
        }
        return results;
    }
}
