package com.hardycherry.filters;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.model.ResourceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterTest extends BaseFilterOperationTest<String, Object> {

    @Test
    public void testFilter1() throws FilterOperationException {
        Filter<String, Object> filter = Filter.<String, Object>builder()
                .isFalse("key1")
                .or(Filter.<String, Object>builder().equals("key2", "value2").equals("key3", "value3").build())
                .build();

        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "false", "key2", "value2"))));
        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "true", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value6"))));
        Assertions.assertEquals("key1=false"+System.lineSeparator()+"OR (key2=value2"+System.lineSeparator()+"key3=value3)", filter.toString());
    }

    @Test
    public void testFilter2() throws FilterOperationException {
        Filter<String, Object> filter = Filter.<String, Object>builder()
                .isTrue("key1")
                .and(Filter.<String, Object>builder().equals("key2", "value2").equals("key3", "value3").build())
                .build();

        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "true", "key2", "value2", "key3", "value3"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "true", "key2", "value", "key3", "value"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "true", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value6"))));
        Assertions.assertEquals("key1=true"+System.lineSeparator()+"AND (key2=value2" + System.lineSeparator() + "key3=value3)", filter.toString());
    }

    @Test
    public void testFilter3() throws FilterOperationException {
        Filter<String, Object> filter = Filter.<String, Object>builder()
                .not(Filter.<String, Object>builder().isTrue("key1").build())
                .build();

        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "false"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "true"))));
        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", new Object()))));
        Assertions.assertEquals("NOT (key1=true)", filter.toString());
    }

    @Test
    public void testFilterGT() throws FilterOperationException {
        Filter<String, Object> filter = Filter.<String, Object>builder()
                .gt("key1", "5")
                .build();

        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "76"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "4"))));
        Assertions.assertEquals("key1 > 5", filter.toString());
    }

    @Test
    public void testFilterLT() throws FilterOperationException {
        Filter<String, Object> filter = Filter.<String, Object>builder()
                .lt("key1", "75")
                .build();

        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "4"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "76"))));
        Assertions.assertEquals("key1 < 75", filter.toString());
    }

    @Test
    public void testFilterRegex() throws FilterOperationException {

        Filter<String, Object> filter = Filter.<String, Object>builder()
                .regex("key1", "value.*").build();

        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "value1"))));
        Assertions.assertTrue(filter.allMatch(buildResource(Map.of("key1", "value test"))));
        Assertions.assertFalse(filter.allMatch(buildResource(Map.of("key1", "test"))));
        Assertions.assertThrows(FilterOperationException.class, () -> filter.allMatch(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> filter.allMatch(null));
        Assertions.assertEquals("key1 ~= value.*", filter.toString());
    }

    @Test
    public void testFilterMultiple() throws FilterOperationException {
        Filter<String, Object> filter = Filter.<String, Object>builder().isFalse("key1").build();

        List<ResourceDto<String, Object>> resources = new ArrayList<>();
        resources.add(buildResource(Map.of("key1", "false")));
        resources.add(buildResource(Map.of("key1", "false")));
        resources.add(buildResource(Map.of("key1", "true")));
        resources.add(buildResource(Map.of("key1", "true")));

        Assertions.assertEquals(2, filter.filterResources(resources).size());
        Assertions.assertEquals(0, filter.filterResources(new ArrayList<>()).size());
        Assertions.assertEquals("key1=false", filter.toString());

    }
}
