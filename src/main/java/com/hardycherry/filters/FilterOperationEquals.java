package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationEquals extends FilterOperation<String, String> {

    public FilterOperationEquals(String right, String left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, ?> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        var foundNull = resource.findKey(rightHandSide) == null || leftHandSide == null;
        if(foundNull) {
            return false;
        }

        return resource.findKey(rightHandSide).getValue().equals(leftHandSide);
    }
}
