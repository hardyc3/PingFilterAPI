package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationFalseTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testFalse() throws FilterOperationException {
        FilterOperationFalse<String, Object> isFalse = new FilterOperationFalse<>("key1");
        Assertions.assertTrue(isFalse.evaluateResource(buildResource(Map.of("key1", "false"))));
        Assertions.assertTrue(isFalse.evaluateResource(buildResource(Map.of("key1", "FALSE"))));
        Assertions.assertTrue(isFalse.evaluateResource(buildResource(Map.of("key1", "False"))));
        Assertions.assertTrue(isFalse.evaluateResource(buildResource(Map.of("key1", false))));
        Assertions.assertFalse(isFalse.evaluateResource(buildResource(Map.of("key1", "value1"))));
        Assertions.assertFalse(isFalse.evaluateResource(buildResource(Map.of("key1", "true"))));
        Assertions.assertFalse(isFalse.evaluateResource(buildResource(Map.of("key1", new Object()))));
        Assertions.assertFalse(isFalse.evaluateResource(buildResource(Map.of("key1", "value1", "key3", "value3"))));
        Assertions.assertThrows(FilterOperationException.class, () -> isFalse.evaluateResource(buildResource(Map.of("key5", "value5", "key6", "value6"))));
        Assertions.assertThrows(FilterOperationException.class, () -> isFalse.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> isFalse.evaluateResource(null));
    }
}
