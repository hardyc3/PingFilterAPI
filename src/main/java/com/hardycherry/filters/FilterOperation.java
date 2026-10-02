package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public abstract class FilterOperation<R, L> {
    R rightHandSide;
    L leftHandSide;

    public abstract boolean evaluateResource(ResourceDto resource) throws FilterOperationException;
}
