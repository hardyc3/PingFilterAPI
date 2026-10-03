package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FilterOperationLessThanTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testLessThan() throws FilterOperationException {

        FilterOperationLessThan lessThan = new FilterOperationLessThan("key1", "key2");

        Assertions.assertFalse(lessThan.evaluateResource(buildResource(Map.of("key1", "1", "key2", "0"))));
        Assertions.assertFalse(lessThan.evaluateResource(buildResource(Map.of("key1", "1.1", "key2", "0.1"))));
        Assertions.assertFalse(lessThan.evaluateResource(buildResource(Map.of("key1", "1203940810923804", "key2", "1203940810923802"))));

        Assertions.assertTrue(lessThan.evaluateResource(buildResource(Map.of("key1", "0", "key2", "1"))));
        Assertions.assertTrue(lessThan.evaluateResource(buildResource(Map.of("key1", "0.1", "key2", "1.1"))));
        Assertions.assertTrue(lessThan.evaluateResource(buildResource(Map.of("key1", "1203940810923801", "key2", "1203940810923802"))));

        Assertions.assertThrows(FilterOperationException.class, () -> lessThan.evaluateResource(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> lessThan.evaluateResource(null));
    }
}
