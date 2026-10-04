package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationGreaterThanTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testGreaterThan() throws FilterOperationException {

        FilterOperationGreaterThan<String, Object> greaterThan = new FilterOperationGreaterThan<>("key1", ".9");

        Assertions.assertTrue(greaterThan.evaluateResource(buildResource(Map.of("key1", "1"))));
        Assertions.assertTrue(greaterThan.evaluateResource(buildResource(Map.of("key1", "1.1"))));
        Assertions.assertTrue(greaterThan.evaluateResource(buildResource(Map.of("key1", "1203940810923804"))));

        Assertions.assertFalse(greaterThan.evaluateResource(buildResource(Map.of("key1", "0"))));
        Assertions.assertFalse(greaterThan.evaluateResource(buildResource(Map.of("key1", "0.1"))));

        Assertions.assertThrows(FilterOperationException.class, () -> greaterThan.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> greaterThan.evaluateResource(null));
    }
}
