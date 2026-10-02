package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationTrue extends FilterOperation<String, Void> {

    public FilterOperationTrue(String right) {
        rightHandSide = right;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
        return (Boolean)resource.findKey(rightHandSide).getValue();
    }
}
