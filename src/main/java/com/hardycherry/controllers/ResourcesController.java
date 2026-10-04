package com.hardycherry.controllers;

import com.hardycherry.exception.FilterOperationException;
import com.hardycherry.exception.FilterParsingException;
import com.hardycherry.model.ResourceDto;
import com.hardycherry.service.ResourcesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("resources")
@Log4j2
public class ResourcesController {

    @Autowired
    ResourcesService resourcesService;

    /**
     *
     * @param filterJsonStr
     * @return resource list that matches filter json
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object retrieveResources(@RequestBody String filterJsonStr) {
        log.info("Using the filter to find matching resources, filterJsonStr=" + filterJsonStr);

        try {
            List<ResourceDto<String, Object>> resourceDtoList = resourcesService.getResourcesWithFilter(filterJsonStr);
            if (resourceDtoList.isEmpty()) {
                return ErrorResponse.builder(null, HttpStatus.BAD_REQUEST, "No resources found with supplied filter");
            } else {
                return resourceDtoList;
            }
        } catch(FilterParsingException | FilterOperationException e) {
            log.error("Error parsing filterJsonStr");
            return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, "Unable to parse json filter");
        }
    }
}
