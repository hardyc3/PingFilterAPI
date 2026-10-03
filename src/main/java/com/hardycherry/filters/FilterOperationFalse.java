package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationFalse extends FilterOperation<String, Void> {

    public FilterOperationFalse(String right) {
        rightHandSide = right;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, ?> resource) throws FilterOperationException {

        if(resource == null || resource.findKey(rightHandSide) == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        var valueObj = resource.findKey(rightHandSide).getValue();
        if(valueObj instanceof Boolean valueBool) {
            return !valueBool;
        } else if(valueObj instanceof String valueStr) {
            return "false".equalsIgnoreCase(valueStr);
        }
        return false;
    }
}
