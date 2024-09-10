package com.example.microservice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(MicroserviceApplication.class)
public class MicroserviceApplicationTest {

    // Create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(MicroserviceApplicationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testServerUp() throws Exception {
        logger.info("Starting testServerUp test");

        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().string("Server is up!"));

        logger.info("Finished testServerUp test");
    }
}
