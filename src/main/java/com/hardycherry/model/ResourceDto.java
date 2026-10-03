package com.hardycherry.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class ResourceDto<K, V> {
    private Integer id;
    private String name;
    private Map<K, ResourceDataDto<K, V>> keyMap;
    private Map<V, ResourceDataDto<K, V>> valueMap;
    private LocalDateTime created;
    private LocalDateTime deleted;

    public ResourceDataDto<K, V> findKey(K key) {
        return keyMap.get(key);
    }

    public ResourceDataDto<K, V> findValue(V value) {
        return valueMap.get(value);
    }
}
