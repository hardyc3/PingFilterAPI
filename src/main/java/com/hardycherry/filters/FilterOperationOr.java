package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public class FilterOperationOr<K, V> extends FilterOperation<K, V> {

    private final Filter<K, V> filters;

    public FilterOperationOr(Filter<K, V> filters) {
        this.filters = filters;
    }

    @Override
    public boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException {
        if(resource == null) {
            throw new FilterOperationException("Resource can't be null");
        }

        return filters.anyMatch(resource);
    }

    @Override
    public String toString() {
        return "OR (" + filters.toString() + ")";
    }
}
