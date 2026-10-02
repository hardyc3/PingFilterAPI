package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterOperationRegex extends FilterOperation<String, String> {

    public FilterOperationRegex(String right, String left) {
        rightHandSide = right;
        leftHandSide = left;
    }

    @Override
    public boolean evaluateResource(ResourceDto resource) throws FilterOperationException {
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
