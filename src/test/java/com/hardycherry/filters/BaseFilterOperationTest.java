package com.hardycherry.filters;

import com.hardycherry.model.ResourceDataDto;
import com.hardycherry.model.ResourceDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BaseFilterOperationTest {

    public ResourceDto buildResource(Map<String, String> map) {

        Map<String, ResourceDataDto> keyMap = new HashMap<>();
        Map<String, ResourceDataDto> valueMap = new HashMap<>();

        for(String key : map.keySet()) {
            var dataDto = ResourceDataDto.builder()
                    .id(1)
                    .key(key)
                    .value(map.get(key))
                    .created(LocalDateTime.now())
                    .build();
            keyMap.put(key, dataDto);
            valueMap.put(map.get(key), dataDto);
        }

        return ResourceDto.builder()
                .name("test")
                .created(LocalDateTime.now())
                .keyMap(keyMap)
                .valueMap(valueMap)
                .build();
    }
}
