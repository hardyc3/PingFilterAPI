package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

public class FilterOperationOr extends FilterOperation {

    @Override
    public boolean evaluateResource(ResourceDto resource) {
        return false;
    }
}
