package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterTest extends BaseFilterOperationTest {

    @Test
    public void testFilter() throws FilterOperationException {
        Filter filter = Filter.builder()
                .isFalse("key1")
                .or(Filter.builder().equals("key2", "value2").build(), Filter.builder().equals("key3", "value3").build())
                .build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "false", "key2", "value2"))));
        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "true", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value6"))));
    }
}
