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
        Filter filter = Filter.builder()
                .isFalse("key1")
                .or(Filter.builder().equals("key2", "value2").build(), Filter.builder().equals("key3", "value3").build())
                .build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "false", "key2", "value2"))));
        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "true", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value6"))));
        Assertions.assertEquals("key1=false"+System.lineSeparator()+"((key2=value2) OR (key3=value3))", filter.toString());
    }

    @Test
    public void testFilter2() throws FilterOperationException {
        Filter<String, String, String, Object> filter = Filter.builder()
                .isTrue("key1")
                .and(Filter.builder().equals("key2", "value2").build(), Filter.builder().equals("key3", "value3").build())
                .build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "true", "key2", "value2", "key3", "value3"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "true", "key2", "value", "key3", "value"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "true", "key2", "value4", "key3", "value3"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "false", "key2", "value4", "key3", "value6"))));
        Assertions.assertEquals("key1=true"+System.lineSeparator()+"((key2=value2) AND (key3=value3))", filter.toString());
    }

    @Test
    public void testFilter3() throws FilterOperationException {
        Filter filter = Filter.builder()
                .not(Filter.builder().isTrue("key1").build())
                .build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "false"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "true"))));
        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", new Object()))));
        Assertions.assertEquals("NOT (key1=true)", filter.toString());
    }

    @Test
    public void testFilterGT() throws FilterOperationException {
        Filter<String, Integer, String, Object> filter = Filter.builder()
                .gt("key1", "key2")
                .build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "76", "key2", "4"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "4", "key2", "54"))));
        Assertions.assertEquals("key1 > key2", filter.toString());
    }

    @Test
    public void testFilterLT() throws FilterOperationException {
        Filter<String, Integer, String, Object> filter = Filter.builder()
                .lt("key1", "key2")
                .build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "4", "key2", "54"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "76", "key2", "4"))));
        Assertions.assertEquals("key1 < key2", filter.toString());
    }

    @Test
    public void testFilterRegex() throws FilterOperationException {

        Filter<String, Integer, String, Object> filter = Filter.builder()
                .regex("key1", "value.*").build();

        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "value1"))));
        Assertions.assertTrue(filter.matches(buildResource(Map.of("key1", "value test"))));
        Assertions.assertFalse(filter.matches(buildResource(Map.of("key1", "test"))));
        Assertions.assertThrows(FilterOperationException.class, () -> filter.matches(buildResource(Map.of())));
        Assertions.assertThrows(FilterOperationException.class, () -> filter.matches(null));
        Assertions.assertEquals("key1 ~= value.*", filter.toString());
    }

    @Test
    public void testFilterMultiple() throws FilterOperationException {
        Filter<String, String, String, Object> filter = Filter.builder().isFalse("key1").build();

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
