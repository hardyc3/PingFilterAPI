package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationEquals extends FilterOperation<String, String> {

    public FilterOperationEquals(String right, String left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
        return resource.findKey(rightHandSide).equals(resource.findValue(leftHandSide));
    }
}
