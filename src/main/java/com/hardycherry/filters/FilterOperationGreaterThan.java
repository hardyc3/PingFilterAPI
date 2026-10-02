package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationGreaterThan extends FilterOperation<String, String> {

    public FilterOperationGreaterThan(String right, String left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
        Object rhsVal = resource.findKey(rightHandSide).getValue();
        Object lhsVal = resource.findKey(leftHandSide).getValue();

        //Todo this could be improved to account for the fact that a double can be compared to a float or an int
        if(rhsVal instanceof Long rhsLong && lhsVal instanceof Long lhsLong) {
            return rhsLong > lhsLong;
        } else if(rhsVal instanceof Integer rhsInt && lhsVal instanceof Integer lhsInt) {
            return rhsInt > lhsInt;
        } else if(rhsVal instanceof Double rhsDbl && lhsVal instanceof Double lhsDbl) {
            return rhsDbl > lhsDbl;
        } else if(rhsVal instanceof Float rhsFlt && lhsVal instanceof Float lhsFlt) {
            return rhsFlt > lhsFlt;
        } else {
            throw new FilterOperationException("Unable to compare types");
        }
    }
}
