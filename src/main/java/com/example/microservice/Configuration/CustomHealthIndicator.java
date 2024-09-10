package com.example.microservice.Configuration;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom logic to check health
        boolean isHealthy = checkSomething();
        if (isHealthy) {
            return Health.up().withDetail("Custom Status", "Everything is OK").build();
        } else {
            return Health.down().withDetail("Custom Status", "Something went wrong").build();
        }
    }

    private boolean checkSomething() {
        // Implement your health check logic here
        return true; // Replace with actual check
    }
}
