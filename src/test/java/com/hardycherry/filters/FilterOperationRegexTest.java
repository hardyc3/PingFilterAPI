package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FilterOperationRegexTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testRegex() throws FilterOperationException {

        FilterOperationRegex<String, Object> regex = new FilterOperationRegex<>("key1", "value.*");
        Assertions.assertTrue(regex.evaluateResource(buildResource(Map.of("key1", "value1"))));
        Assertions.assertTrue(regex.evaluateResource(buildResource(Map.of("key1", "value test"))));
        Assertions.assertFalse(regex.evaluateResource(buildResource(Map.of("key1", "test"))));
        Assertions.assertThrows(FilterOperationException.class, () -> regex.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> regex.evaluateResource(null));
    }
}
