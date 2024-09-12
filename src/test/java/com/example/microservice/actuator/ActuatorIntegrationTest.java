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
public class ActuatorIntegrationTest {

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
        // Additional validation based on expected response
        JsonNode components = jsonResponse.path("components");
        assertEquals("UP", components.path("diskSpace").path("status").asText());
        assertEquals("UP", components.path("ping").path("status").asText());
    }

    @Test
    public void testInfoEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/info", String.class);
        assertEquals(200, response.getStatusCode().value());

        String body = response.getBody();
        assertNotNull(body);
        // Additional validation based on expected response
    }

    @Test
    public void testMetricsEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/metrics", String.class);
        assertEquals(200, response.getStatusCode().value());

        String body = response.getBody();
        assertNotNull(body);
        // Additional validation based on expected response
    }
}
