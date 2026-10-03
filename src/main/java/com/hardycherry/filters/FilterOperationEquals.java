package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationEquals<L, V> extends FilterOperation<String, L, String, V> {

    public FilterOperationEquals(String right, L left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        var foundNull = resource.findKey(rightHandSide) == null || leftHandSide == null;
        if(foundNull) {
            return false;
        }

        return resource.findKey(rightHandSide).getValue().equals(leftHandSide);
    }

    @Override
    public String toString() {
        return rightHandSide.toString() + "=" + leftHandSide.toString();
    }
}
