package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationIsPresentTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testIsPresent() throws FilterOperationException {
        FilterOperationIsPresent<String, Object> isPresent = new FilterOperationIsPresent<>("key1");

        Assertions.assertTrue(isPresent.evaluateResource(buildResource(Map.of("key1", "value1", "key2", "value2"))));
        Assertions.assertFalse(isPresent.evaluateResource(buildResource(Map.of("key2", "value2"))));
        Assertions.assertFalse(isPresent.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> isPresent.evaluateResource(null));
    }
}
