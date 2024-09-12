package com.example.microservice.actuator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActuatorEndpointsTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testHealthEndpoint() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);
        assertEquals(200, response.getStatusCode().value()); // Check HTTP status code

        String body = response.getBody();
        assertNotNull(body); // Ensure the body is not null

        // Parse JSON response
        JsonNode jsonResponse = objectMapper.readTree(body);

        // Validate JSON content
        assertEquals("UP", jsonResponse.path("status").asText());
        JsonNode components = jsonResponse.path("components");
        assertEquals("UP", components.path("diskSpace").path("status").asText());
        assertEquals("UP", components.path("ping").path("status").asText());
    }

    @Test
    public void testInfoEndpoint() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/info", String.class);
        assertEquals(200, response.getStatusCode().value());

        String body = response.getBody();
        assertNotNull(body);

        // Parse JSON response
        JsonNode jsonResponse = objectMapper.readTree(body);

        // Validate JSON content based on your expected structure
        // Example: Check if 'app' field exists
        assertNotNull(jsonResponse.path("app").asText(), "App information should be present");
    }

    @Test
    public void testMetricsEndpoint() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/metrics", String.class);
        assertEquals(200, response.getStatusCode().value());

        String body = response.getBody();
        assertNotNull(body);

        // Parse JSON response
        JsonNode jsonResponse = objectMapper.readTree(body);

        // Validate JSON content based on your expected structure
        // Example: Check if 'jvm.memory.used' metric is present
        assertNotNull(jsonResponse.path("jvm.memory.used").asText(), "JVM memory used metric should be present");
    }
}
