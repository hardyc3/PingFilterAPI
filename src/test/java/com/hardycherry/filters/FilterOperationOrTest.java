package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationOrTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testOr() throws FilterOperationException {

        var right = new Filter.Builder().isPresent("key1").build();
        var left = new Filter.Builder().isPresent("key2").build();

        FilterOperationOr and = new FilterOperationOr(right, left);
        Assertions.assertTrue(and.evaluateResource(buildResource(Map.of("key1", "value1", "key2", "value2"))));
        Assertions.assertTrue(and.evaluateResource(buildResource(Map.of("key1", "value1", "key3", "value3"))));
        Assertions.assertTrue(and.evaluateResource(buildResource(Map.of("key5", "value5", "key2", "value2"))));
        Assertions.assertFalse(and.evaluateResource(buildResource(Map.of("key5", "value5", "key6", "value6"))));
        Assertions.assertFalse(and.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> and.evaluateResource(null));
    }
}
