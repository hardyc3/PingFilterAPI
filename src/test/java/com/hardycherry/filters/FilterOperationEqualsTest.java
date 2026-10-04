package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationEqualsTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testEqualsByKey() throws FilterOperationException {

        FilterOperationEquals<String, Object> equals = new FilterOperationEquals<>("key1", "value1");
        Assertions.assertTrue(equals.evaluateResource(buildResource(Map.of("key1", "value1", "key2", "value1"))));
        Assertions.assertTrue(equals.evaluateResource(buildResource(Map.of("key1", "value1"))));
        Assertions.assertFalse(equals.evaluateResource(buildResource(Map.of("key2", "value2"))));
        Assertions.assertFalse(equals.evaluateResource(buildResource(Map.of("key1", "value5", "key3", "value3"))));
        Assertions.assertFalse(equals.evaluateResource(buildResource(Map.of("key5", "value5", "key6", "value6"))));
        Assertions.assertFalse(equals.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> equals.evaluateResource(null));
    }
}
