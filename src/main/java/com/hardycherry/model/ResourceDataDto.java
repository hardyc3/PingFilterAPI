package com.hardycherry.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ResourceDataDto<K, V> {
    private Integer id;
    private K key;
    private V value;
    private LocalDateTime created;
    private LocalDateTime deleted;
}
