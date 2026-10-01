package com.hardycherry.filters;

import com.hardycherry.model.ResourceDto;

public abstract class FilterOperation {
    String rightHandSide;
    String leftHandSide;

    public abstract boolean evaluateResource(ResourceDto resource);
}
