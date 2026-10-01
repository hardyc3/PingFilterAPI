package com.hardycherry.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ResourceDataDto {
    private Integer id;
    private String key;
    private String value;
    private LocalDateTime created;
    private LocalDateTime deleted;
}
