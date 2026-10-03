package com.hardycherry.filters;

import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FilterParser {

    public Filter parse(String filter) {
        GsonJsonParser parser = new GsonJsonParser();
        Map<String, Object> jsonData = parser.parseMap(filter);

        Filter.Builder builder = Filter.builder();
        for(String key : jsonData.keySet()) {

        }

        return builder.build();
    }
}
