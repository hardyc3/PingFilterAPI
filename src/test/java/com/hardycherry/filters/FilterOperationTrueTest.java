package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationTrueTest  extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testTrue() throws FilterOperationException {
        FilterOperationTrue isTrue = new FilterOperationTrue("key1");
        Assertions.assertTrue(isTrue.evaluateResource(buildResource(Map.of("key1", "true"))));
        Assertions.assertTrue(isTrue.evaluateResource(buildResource(Map.of("key1", "TRUE"))));
        Assertions.assertTrue(isTrue.evaluateResource(buildResource(Map.of("key1", "True"))));
        Assertions.assertFalse(isTrue.evaluateResource(buildResource(Map.of("key1", "value1"))));
        Assertions.assertFalse(isTrue.evaluateResource(buildResource(Map.of("key1", "false"))));
        Assertions.assertFalse(isTrue.evaluateResource(buildResource(Map.of("key1", "value1", "key3", "value3"))));
        Assertions.assertThrows(FilterOperationException.class, () -> isTrue.evaluateResource(buildResource(Map.of("key5", "value5", "key6", "value6"))));
        Assertions.assertThrows(FilterOperationException.class, () -> isTrue.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> isTrue.evaluateResource(null));
    }
}
