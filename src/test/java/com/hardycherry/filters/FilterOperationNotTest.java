package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationNotTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testNot() throws FilterOperationException {

        var isPresent = Filter.<String, Object>builder().isPresent("key1").build();
        FilterOperationNot<String, Object> not = new FilterOperationNot<>(isPresent);
        Assertions.assertFalse(not.evaluateResource(buildResource(Map.of("key1", "value1"))));
        Assertions.assertTrue(not.evaluateResource(buildResource(Map.of( "key3", "value3"))));
        Assertions.assertTrue(not.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> not.evaluateResource(null));
    }
}
