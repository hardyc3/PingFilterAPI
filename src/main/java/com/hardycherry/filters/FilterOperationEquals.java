package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

public class FilterOperationEquals extends FilterOperation {

    @Override
    public boolean evaluateResource(ResourceDto resource) {
        return resource.findKey(rightHandSide).equals(resource.findValue(leftHandSide));
    }
}
