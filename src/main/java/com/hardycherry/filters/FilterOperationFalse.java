package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationFalse extends FilterOperation<String, Void> {

    public FilterOperationFalse(String right) {
        rightHandSide = right;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
        return !(Boolean)resource.findKey(rightHandSide).getValue();
    }
}
