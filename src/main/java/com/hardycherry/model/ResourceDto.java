package com.hardycherry.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ResourceDto {
    private Integer id;
    private String name;
    private Map<String, String> resourceData;
    private LocalDateTime created;
    private LocalDateTime deleted;
}
