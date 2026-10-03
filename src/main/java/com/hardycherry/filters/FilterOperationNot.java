package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationNot<K, V> extends FilterOperation<Filter, Void, K, V> {

    public FilterOperationNot(Filter<?, ?, K, V> filter) {
        rightHandSide = filter;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        return !rightHandSide.matches(resource);
    }
}
