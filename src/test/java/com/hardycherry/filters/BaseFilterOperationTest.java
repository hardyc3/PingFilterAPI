package com.hardycherry.filters;

import com.hardycherry.model.ResourceDataDto;
import com.hardycherry.model.ResourceDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BaseFilterOperationTest <K, V> {

    public ResourceDto<K, V> buildResource(Map<K, V> map) {

        Map<K, ResourceDataDto<K, V>> keyMap = new HashMap<>();
        Map<V, ResourceDataDto<K, V>> valueMap = new HashMap<>();

        for(K key : map.keySet()) {
            var dataDto = ResourceDataDto.<K, V>builder()
                    .id(1)
                    .key(key)
                    .value(map.get(key))
                    .created(LocalDateTime.now())
                    .build();
            keyMap.put(key, dataDto);
            valueMap.put(map.get(key), dataDto);
        }

        return ResourceDto.<K, V>builder()
                .name("test")
                .created(LocalDateTime.now())
                .keyMap(keyMap)
                .valueMap(valueMap)
                .build();
    }
}
