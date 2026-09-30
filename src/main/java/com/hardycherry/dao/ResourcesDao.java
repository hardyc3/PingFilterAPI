package com.hardycherry.dao;

import com.hardycherry.db.jooq.org.jooq.generated.tables.ResourceData;
import com.hardycherry.db.jooq.org.jooq.generated.tables.Resources;
import com.hardycherry.model.ResourceDto;
import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hardycherry.db.jooq.org.jooq.generated.tables.records.ResourcesRecord;
import com.hardycherry.db.jooq.org.jooq.generated.tables.records.*;

import java.util.List;
import java.util.Map;

@Component
@Log4j2
public class ResourcesDao {
    @Autowired
    DSLContext dslContext;

    public List<ResourceDto> getAllResources() {
        List<Map<ResourcesRecord, ResourceDataRecord>> recordsAndData = dslContext.select()
                .from(Resources.RESOURCES
                        .join(ResourceData.RESOURCE_DATA)
                        .on(Resources.RESOURCES.ID.eq(ResourceData.RESOURCE_DATA.RESOURCE_ID)))
                .fetch()
                .map(record -> Map.of(record.into(ResourcesRecord.class), record.into(ResourceDataRecord.class)));

        for(Map<ResourcesRecord, ResourceDataRecord> record : recordsAndData) {

        }
    }
}
