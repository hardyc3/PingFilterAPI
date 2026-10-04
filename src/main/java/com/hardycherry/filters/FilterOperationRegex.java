package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

import java.util.regex.Pattern;

public class FilterOperationRegex<K, V> extends FilterOperation<K, V> {

    private final K key;
    private final String regex;

    public FilterOperationRegex(K key, String regex) {
        this.key = key;
        this.regex = regex;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {

        if(resource == null || resource.findKey(key) == null || regex == null) {
            throw new FilterOperationException("Resource and parameters can't be null");
        }

        Object keyObj = resource.findKey(key).getValue();

        if(keyObj instanceof String keyValue) {
            return Pattern.compile(regex).matcher(keyValue).matches();
        } else {
            throw new FilterOperationException("resource value is not a string and can't have a regex applied to it");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(key) + " ~= " + regex;
    }
}
