package com.hardycherry.controllers;

import lombok.extern.log4j.Log4j2;
import org.jooq.DSLContext;
import org.jooq.generated.tables.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("greeting")
@Log4j2
public class GreetingsController {

    @Autowired
    DSLContext dslContext;

    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable("name") String name) {
        log.info("Generating a greeting");
        List<org.jooq.generated.tables.records.ResourcesRecord> resources = dslContext.select().from(Resources.RESOURCES). fetchInto(org.jooq.generated.tables.records.ResourcesRecord.class);

        return "Hello " + name + "!";
    }
}
