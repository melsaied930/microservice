package com.example.microservice.actuator;

import com.example.microservice.MicroserviceApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MicroserviceApplication.class)
public class ActuatorIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testHealthEndpoint() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);
        assertEquals(200, response.getStatusCode().value());

        String body = response.getBody();
        assertNotNull(body);

        JsonNode jsonResponse = objectMapper.readTree(body);
        assertEquals("UP", jsonResponse.path("status").asText());

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
    }

    @Test
    public void testMetricsEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator/metrics", String.class);
        assertEquals(200, response.getStatusCode().value());

        String body = response.getBody();
        assertNotNull(body);
    }
}
