package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

public class FilterOperationNot extends FilterOperation {

    @Override
    public boolean evaluateResource(ResourceDto resource) {
        return false;
    }
}
