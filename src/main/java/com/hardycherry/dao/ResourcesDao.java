package com.hardycherry.dao;

import com.hardycherry.generated.tables.ResourceData;
import com.hardycherry.generated.tables.Resources;
import com.hardycherry.generated.tables.records.ResourceDataRecord;
import com.hardycherry.generated.tables.records.ResourcesRecord;
import com.hardycherry.model.ResourceDataDto;
import com.hardycherry.model.ResourceDto;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
public class ResourcesDao {
    @Autowired
    DSLContext dslContext;

    public List<ResourceDto<String, ? super Object>> getAllResources() {
        Map<ResourcesRecord, Result<ResourceDataRecord>> recordsAndData = dslContext.select()
                .from(Resources.RESOURCES.join(ResourceData.RESOURCE_DATA)
                        .on(Resources.RESOURCES.ID.eq(ResourceData.RESOURCE_DATA.RESOURCE_ID)))
                .fetch()
                .intoGroups(Resources.RESOURCES, ResourceData.RESOURCE_DATA);

        List<ResourceDto<String, ? super Object>> results = new ArrayList<>();
        for(ResourcesRecord resourcesRecord : recordsAndData.keySet()) {
            List<ResourceDataRecord> resourceDataRecords = recordsAndData.get(resourcesRecord);
            Map<String, ResourceDataDto<String, Object>> keyMap = new HashMap<>();
            Map<Object, ResourceDataDto<String, Object>> valueMap = new HashMap<>();
            for(ResourceDataRecord dataRecord : resourceDataRecords) {
                ResourceDataDto<String, Object> dataDto = ResourceDataDto.<String, Object>builder()
                        .id(dataRecord.getId())
                        .key(dataRecord.getResourceKey())
                        .value(dataRecord.getResourceValue())
                        .created(dataRecord.getCreated())
                        .deleted(dataRecord.getDeleted())
                        .build();

                keyMap.put(dataRecord.getResourceKey(), dataDto);
                valueMap.put(dataRecord.getResourceValue(), dataDto);
            }

            results.add(ResourceDto.<String, Object>builder()
                    .id(resourcesRecord.getId())
                    .name(resourcesRecord.getName())
                    .created(resourcesRecord.getCreated())
                    .deleted(resourcesRecord.getDeleted())
                    .keyMap(keyMap)
                    .valueMap(valueMap)
                    .build());
        }
        return results;
    }
}
