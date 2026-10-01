package com.hardycherry.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class ResourceDto {
    private Integer id;
    private String name;
    private Map<String, ResourceDataDto> keyMap;
    private Map<String, ResourceDataDto> valueMap;
    private LocalDateTime created;
    private LocalDateTime deleted;

    public ResourceDataDto findKey(String key) {
        return keyMap.get(key);
    }

    public ResourceDataDto findValue(String value) {
        return valueMap.get(value);
    }
}
