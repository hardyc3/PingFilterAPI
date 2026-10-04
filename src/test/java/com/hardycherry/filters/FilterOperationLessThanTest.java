package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationLessThanTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testLessThan() throws FilterOperationException {

        FilterOperationLessThan<String, Object> lessThan = new FilterOperationLessThan<>("key1", "0");

        Assertions.assertFalse(lessThan.evaluateResource(buildResource(Map.of("key1", "1"))));
        Assertions.assertFalse(lessThan.evaluateResource(buildResource(Map.of("key1", "1.1"))));
        Assertions.assertFalse(lessThan.evaluateResource(buildResource(Map.of("key1", "1203940810923804"))));

        Assertions.assertTrue(lessThan.evaluateResource(buildResource(Map.of("key1", "-0.1"))));

        Assertions.assertThrows(FilterOperationException.class, () -> lessThan.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> lessThan.evaluateResource(null));
    }
}
