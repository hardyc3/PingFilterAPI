package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationAnd<K, V> extends FilterOperation<K, V> {

    private final Filter<K, V> rightHandSide;
    private final Filter<K, V> leftHandSide;

    public FilterOperationAnd(Filter<K, V> right, Filter<K, V> left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        return rightHandSide.matches(resource) && leftHandSide.matches(resource);
    }

    @Override
    public String toString() {
        return "((" + rightHandSide.toString() + ") AND (" + leftHandSide.toString() + "))";
    }
}
