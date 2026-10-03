package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationIsPresent<V> extends FilterOperation<String, Void, String, V> {

    public FilterOperationIsPresent(String right) {
        rightHandSide = right;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, V> resource) throws FilterOperationException {
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

    @Override
    public String toString() {
        return rightHandSide.toString() + " is present";
    }
}
