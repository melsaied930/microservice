plugins {
    java
    id("org.springframework.boot") version "3.3.3"  // Spring Boot plugin
    id("io.spring.dependency-management") version "1.1.6"  // Dependency management plugin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))  // Set Java toolchain to version 22
    }
}

repositories {
    mavenCentral()  // Use Maven Central repository for dependencies
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-actuator")  // Actuator for monitoring and management
    implementation("org.springframework.boot:spring-boot-starter-web")  // Web application starter
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")  // Thymeleaf for template rendering

    // JPA and Hibernate for relational database support (commented out)
    // implementation("org.springframework.boot:spring-boot-starter-data-jpa")  // JPA starter
    // implementation("org.hibernate.orm:hibernate-core:6.2.8.Final")  // Hibernate ORM (not required if using MongoDB)

    // MongoDB support
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")  // MongoDB starter

    // Lombok for code simplification
    compileOnly("org.projectlombok:lombok")  // Lombok for compile-time code generation
    annotationProcessor("org.projectlombok:lombok")  // Lombok annotation processor

    // Development tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")  // Devtools for enhanced development experience

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")  // Spring Boot testing support
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")  // JUnit Platform Launcher for testing

    // Embedded MongoDB for testing
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.17.0")  // Embedded MongoDB for integration tests

    // OpenAPI support
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")  // OpenAPI UI for Spring Web MVC
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")  // OpenAPI UI for Spring WebFlux
    // SpringDoc OpenAPI for Swagger
//    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")  // Use the latest version
}

tasks.withType<Test> {
    useJUnitPlatform()  // Use JUnit Platform for running tests
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading")  // Enable dynamic agent loading
}
