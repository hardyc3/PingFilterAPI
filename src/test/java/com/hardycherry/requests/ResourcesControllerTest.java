package com.hardycherry.requests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
public class ResourcesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Value("classpath:filterAnd.json")
    private Resource andJsonResource;
    @Value("classpath:filterOr.json")
    private Resource orJsonResource;
    @Value("classpath:filterNot.json")
    private Resource notJsonResource;
    @Value("classpath:filterComplex.json")
    private Resource complexJsonResource;

    @Test
    public void getResourcesWithFilter() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(andJsonResource.getContentAsString(Charset.defaultCharset())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertEquals(response, "Hello Java!");
    }

}
