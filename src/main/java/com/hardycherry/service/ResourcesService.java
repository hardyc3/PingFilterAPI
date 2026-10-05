package com.hardycherry.service;

import com.hardycherry.dao.ResourcesDao;
import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.exception.FilterParsingException;
import com.hardycherry.filters.Filter;
import com.hardycherry.filters.FilterParser;
import com.hardycherry.model.ResourceDto;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

@Component
@Log4j2
public class ResourcesService {

    @Autowired
    private FilterParser parser;

    @Autowired
    private ResourcesDao resourcesDao;

    @Autowired
    private RedisCommands<String, String> redisCommands;

    @Autowired
    private ObjectMapper mapper;

    public List<ResourceDto<String, Object>> getResourcesWithFilter(String filterJsonStr) throws FilterParsingException, FilterOperationException {

        log.info("Parsing json and filtering all resources in db, json=" + filterJsonStr);

        if(StringUtils.isBlank(filterJsonStr)) {
            log.info("filterJsonStr was null or empty");
            return Collections.emptyList();
        }

        String results = redisCommands.get(filterJsonStr);
        if(results != null) {
            var resultsList = mapper.readValue(results, List.class);
            return resultsList;
        } else {
            Filter<String, Object> filter = parser.parse(filterJsonStr);
            if (filter != null) {
                List<ResourceDto<String, Object>> resourceDtoList = resourcesDao.getAllResources();
                var resultList = filter.filterResources(resourceDtoList);
                redisCommands.set(filterJsonStr, mapper.writeValueAsString(resultList));
                return resultList;
            } else {
                return Collections.emptyList();
            }
        }
    }
}
