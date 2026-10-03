package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterOperationRegex<V> extends FilterOperation<String, String, String, V> {

    public FilterOperationRegex(String right, String left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto<String, V> resource) throws FilterOperationException {

        if(resource == null || resource.findKey(rightHandSide) == null || leftHandSide == null) {
            throw new FilterOperationException("Resource and parameters can't be null");
        }

        Object keyObj = resource.findKey(rightHandSide).getValue();

        if(keyObj instanceof String key) {
            Pattern p = Pattern.compile(leftHandSide);
            Matcher m = p.matcher(key);
            return m.matches();
        } else {
            throw new FilterOperationException("resource value is not a string and can't have a regex applied to it");
        }
    }
}
