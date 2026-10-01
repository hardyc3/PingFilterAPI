package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

public class FilterOperationFalse extends FilterOperation {

    @Override
    public boolean evaluateResource(ResourceDto resource) {
        return false;
    }
}
