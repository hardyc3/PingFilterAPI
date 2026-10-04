package com.hardycherry.requests;

import com.hardycherry.generated.tables.ResourceData;
import com.hardycherry.generated.tables.Resources;
import com.hardycherry.generated.tables.records.ResourceDataRecord;
import com.hardycherry.generated.tables.records.ResourcesRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.*;

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
    @Autowired
    private DSLContext dslContext;

    @BeforeEach
    public void before() {
        dslContext.deleteFrom(ResourceData.RESOURCE_DATA).execute();
        dslContext.deleteFrom(Resources.RESOURCES).execute();
    }

    @Test
    public void getResourcesWithANDFilter() throws Exception {
        Random r = new Random();
        Map<String, String> data1 = Map.of("user", "fred", "age", "33", "role", "admin");
        Map<String, String> data2 = Map.of("user", "max", "age", "33", "role", "admin");
        createResource(data1, r.nextInt());
        createResource(data2, r.nextInt());
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(andJsonResource.getContentAsString(Charset.defaultCharset())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertTrue(response.contains("value\":\"fred"));
        Assertions.assertFalse(response.contains("value\":\"max"));
    }

    @Test
    public void getResourcesWithORFilter() throws Exception {
        Random r = new Random();
        Map<String, String> data1 = Map.of("user", "fred", "age", "33", "role", "admin");
        Map<String, String> data2 = Map.of("user", "max", "age", "33", "role", "admin");
        createResource(data1, r.nextInt());
        createResource(data2, r.nextInt());
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orJsonResource.getContentAsString(Charset.defaultCharset())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertTrue(response.contains("value\":\"fred"));
        Assertions.assertTrue(response.contains("value\":\"max"));
    }

    @Test
    public void getResourcesWithNOTFilter() throws Exception {
        Random r = new Random();
        Map<String, String> data1 = Map.of("user", "fred", "age", "33", "role", "admin");
        Map<String, String> data2 = Map.of("user", "max", "age", "33", "role", "admin");
        Map<String, String> data3 = Map.of("user", "jane", "age", "33", "role", "admin");
        createResource(data1, r.nextInt());
        createResource(data2, r.nextInt());
        createResource(data3, r.nextInt());
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(notJsonResource.getContentAsString(Charset.defaultCharset())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertFalse(response.contains("value\":\"fred"));
        Assertions.assertTrue(response.contains("value\":\"max"));
        Assertions.assertTrue(response.contains("value\":\"jane"));
    }

    @Test
    public void getResourcesWithComplexFilter() throws Exception {
        Random r = new Random();
        Map<String, String> data1 = Map.of("user", "fred", "lastname", "maxwell", "age", "43", "role", "manager", "isCool", "true", "isAdmin", "false");
        Map<String, String> data2 = Map.of("user", "fred", "lastname", "maximillion", "age", "43", "role", "manager", "isCool", "true", "isAdmin", "false");
        Map<String, String> data3 = Map.of("user", "fred", "lastname", "maxy", "age", "60", "role", "manager", "isCool", "true", "isAdmin", "false");
        Map<String, String> data4 = Map.of("user", "fred", "lastname", "maximum", "age", "30", "role", "manager", "isCool", "true", "isAdmin", "false");
        createResource(data1, r.nextInt());
        createResource(data2, r.nextInt());
        createResource(data3, r.nextInt());
        createResource(data4, r.nextInt());
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(complexJsonResource.getContentAsString(Charset.defaultCharset())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertTrue(response.contains("value\":\"maxwell"));
        Assertions.assertTrue(response.contains("value\":\"maximillion"));
        Assertions.assertFalse(response.contains("value\":\"maxy"));
        Assertions.assertFalse(response.contains("value\":\"maximum"));
    }

    private void createResource(Map<String, String> data, Integer id) {
        ResourcesRecord resource = new ResourcesRecord();
        resource.setId(id);
        resource.setName("test"+id);
        dslContext.executeInsert(resource);

        for(String key : data.keySet()) {
            ResourceDataRecord dataRecord = new ResourceDataRecord();
            dataRecord.setResourceId(resource.getId());
            dataRecord.setResourceKey(key);
            dataRecord.setResourceValue(data.get(key));
            dslContext.executeInsert(dataRecord);
        }
    }
}
