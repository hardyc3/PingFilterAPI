package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationOr extends FilterOperation<Filter, Filter> {

    public FilterOperationOr(Filter right, Filter left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
        if(resource == null) {
            return false;
        }
        
        return rightHandSide.matches(resource) || leftHandSide.matches(resource);
    }
}
