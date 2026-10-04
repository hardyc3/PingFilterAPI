package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FilterOperationRegexWithBooleansTest extends BaseFilterOperationTest<String, Boolean> {

    @Test
    public void testRegex() throws FilterOperationException {

        HashMap<String, Boolean> data = new HashMap<>();
        data.put("test1", true);
        data.put("test2", false);

        FilterOperationRegex<String, Boolean> regex = new FilterOperationRegex<>("test1", "value.*");
        Assertions.assertThrows(FilterOperationException.class, () -> regex.evaluateResource(buildResource(data)));
    }
}