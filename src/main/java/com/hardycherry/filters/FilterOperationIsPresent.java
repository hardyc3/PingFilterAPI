package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationIsPresent extends FilterOperation<String, Void> {

    public FilterOperationIsPresent(String right) {
        rightHandSide = right;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
        var key = resource.findKey(rightHandSide);
        if(key != null) {
            return key.getValue() != null;
        } else {
            return false;
        }
    }
}
