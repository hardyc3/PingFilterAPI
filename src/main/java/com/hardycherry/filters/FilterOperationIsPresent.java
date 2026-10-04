package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationIsPresent<K, V> extends FilterOperation<K, V> {

    private final K key;

    public FilterOperationIsPresent(K key) {
        this.key = key;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        var found = resource.findKey(key);
        if(found != null) {
            return found.getValue() != null;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(key) + " is present";
    }
}
