package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationIsPresent extends FilterOperation<String, Void> {

    public FilterOperationIsPresent(String right) {
        rightHandSide = right;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, ?> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        var key = resource.findKey(rightHandSide);
        if(key != null) {
            return key.getValue() != null;
        } else {
            return false;
        }
    }
}
