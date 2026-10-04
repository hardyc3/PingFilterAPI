package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationAnd<K, V> extends FilterOperation<K, V> {

    private final Filter<K, V> filters;

    public FilterOperationAnd(Filter<K, V> filters) {
        this.filters = filters;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        return filters.allMatch(resource);
    }

    @Override
    public String toString() {
        return "AND (" + filters.toString() + ")";
    }
}
