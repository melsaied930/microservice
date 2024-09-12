package com.example.microservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MicroserviceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testServerUpEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/server", String.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("serverUp!", response.getBody());
    }
}
