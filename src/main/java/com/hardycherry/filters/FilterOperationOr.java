package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationOr<V> extends FilterOperation<Filter<?, ?, String, V>, Filter<?, ?, String, V>, String, V> {

    public FilterOperationOr(Filter<?, ?, String, V> right, Filter<?, ?, String, V> left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        return rightHandSide.matches(resource) || leftHandSide.matches(resource);
    }
}
