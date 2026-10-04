package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationGreaterThan<K, V> extends FilterOperation<K, V> {

    private final K rightKey;
    private final K leftKey;

    public FilterOperationGreaterThan(K right, K left) {
        rightKey = right;
        leftKey = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null || resource.findKey(rightKey) == null || resource.findKey(leftKey) == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        String rhsVal = String.valueOf(resource.findKey(rightKey).getValue());
        String lhsVal = String.valueOf(resource.findKey(leftKey).getValue());
        var rhs = Double.parseDouble(rhsVal);
        var lhs = Double.parseDouble(lhsVal);

        return rhs > lhs;
    }

    @Override
    public String toString() {
        return String.valueOf(rightKey) + " > " + String.valueOf(leftKey);
    }
}
