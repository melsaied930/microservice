package com.example.microservice.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot 3 API")
                        .version("1.0")
                        .description("Spring Boot project with Swagger/OpenAPI integration")
                        .contact(new Contact()
                                .name("John Doe")
                                .email("johndoe@example.com")
                                .url("https://example.com")));
    }
}