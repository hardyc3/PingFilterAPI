package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;

public abstract class FilterOperation<K, V> {

    public abstract boolean evaluateResource(ResourceDto<K, V> resource) throws FilterOperationException;
}
