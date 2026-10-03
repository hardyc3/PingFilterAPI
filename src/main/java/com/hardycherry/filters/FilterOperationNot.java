package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationNot extends FilterOperation<Filter, Void> {

    public FilterOperationNot(Filter filter) {
        rightHandSide = filter;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, ?> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        return !rightHandSide.matches(resource);
    }
}
