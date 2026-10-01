package com.hardycherry.controllers;

import com.hardycherry.dao.ResourcesDao;
import com.hardycherry.model.ResourceDto;
import com.hardycherry.service.ResourcesService;
import jakarta.ws.rs.client.Entity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
     * @param filter
     * @return greeting text
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Object retrieveResources(@RequestParam String filter) {
        log.info("Using the filter to find matching resources, filter=" + filter);

        List<ResourceDto> resourceDtoList = resourcesService.getResourcesWithFilter(filter);
        if(resourceDtoList.isEmpty()) {
            return ErrorResponse.builder(null, HttpStatus.BAD_REQUEST, "No resources found with supplied filter");
        } else {
            return resourceDtoList;
        }
    }
}
