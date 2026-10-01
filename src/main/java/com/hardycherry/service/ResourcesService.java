package com.hardycherry.service;

import com.hardycherry.dao.ResourcesDao;
import com.hardycherry.filters.Filter;
import com.hardycherry.filters.FilterParser;
import com.hardycherry.model.ResourceDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ResourcesService {

    @Autowired
    private ResourcesDao resourcesDao;

    public List<ResourceDto> getResourcesWithFilter(String filterStr) {

        if(StringUtils.isBlank(filterStr)) {
            return Collections.EMPTY_LIST;
        }

        //todo: chaching

        Filter filter = FilterParser.parse(filterStr);
        if(filter.isValid()) {
            List<ResourceDto> resourceDtoList = resourcesDao.getAllResources();
            return filter.filterResources(resourceDtoList);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
