package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationLessThan<V> extends FilterOperation<String, String, String, V> {

    public FilterOperationLessThan(String right, String left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, V> resource) throws FilterOperationException {
        if(resource == null || resource.findKey(rightHandSide) == null || resource.findKey(leftHandSide) == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        String rhsVal = (String)resource.findKey(rightHandSide).getValue();
        String lhsVal = (String)resource.findKey(leftHandSide).getValue();
        var rhs = Double.parseDouble(rhsVal);
        var lhs = Double.parseDouble(lhsVal);

        return rhs < lhs;
    }
}
