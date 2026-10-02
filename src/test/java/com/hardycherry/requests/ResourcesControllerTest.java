package com.hardycherry.requests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ResourcesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * @throws Exception
     *
     * It tests response to be "Hello Java!"
     */
    @Test
    public void greetJava() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/greeting/Java"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertEquals(response, "Hello Java!");
    }

    /**
     *
     * @throws Exception
     *
     * It tests response to be "Hello Spring!"
     */
    @Test
    public void greetSpring() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/greeting/Spring"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertEquals(response, "Hello Spring!");
    }

    /**
     *
     * @throws Exception
     *
     * It tests response to be "Hello RodJohnson!"
     */
    @Test
    public void greetRodJohnson() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/greeting/RodJohnson"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertEquals(response, "Hello RodJohnson!");
    }
}
