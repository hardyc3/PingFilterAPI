package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationEquals<K, V> extends FilterOperation<K, V> {

    private final K key;
    private final V value;

    public FilterOperationEquals(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }
        var found = resource.findKey(key);
        if(found == null || value == null) {
            return false;
        }

        return value.equals(found.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(key) + "=" + String.valueOf(value);
    }
}
