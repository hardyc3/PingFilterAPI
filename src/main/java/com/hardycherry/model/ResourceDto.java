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
    private List<ResourceDataDto> resourceDataList;
    private LocalDateTime created;
    private LocalDateTime deleted;
}
