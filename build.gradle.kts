plugins {
    java
    kotlin("jvm") version "1.8.10" // Kotlin JVM plugin for Kotlin support
    id("org.springframework.boot") version "3.3.3" // Spring Boot plugin for bootstrapping
    id("io.spring.dependency-management") version "1.1.6" // Spring Dependency Management plugin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22)) // Use Java toolchain version 22
    }
}

springBoot {
    buildInfo() // Generate build information for Spring Boot Actuator's `/info` endpoint
}

repositories {
    mavenCentral() // Use Maven Central as the repository for dependencies
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-actuator") // Actuator for monitoring and management
    implementation("org.springframework.boot:spring-boot-starter-web") // Starter for building web applications

    // MongoDB Support
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb") // Starter for MongoDB support

    // Lombok for Code Simplification
    compileOnly("org.projectlombok:lombok") // Compile-time Lombok annotations for reducing boilerplate code
    annotationProcessor("org.projectlombok:lombok") // Lombok annotation processor

    // Development Tools
    developmentOnly("org.springframework.boot:spring-boot-devtools") // Devtools for hot-reloading and other dev-time utilities

    // Testing Dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") // Starter for testing support
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") // JUnit Platform Launcher for running tests

    // Embedded MongoDB for integration testing
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.17.0") // Embedded MongoDB for testing

    // OpenAPI / Swagger Support
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0") // OpenAPI UI for Spring Web MVC
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0") // OpenAPI UI for Spring WebFlux
}

tasks.withType<Test> {
    useJUnitPlatform() // Use JUnit Platform for running tests
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading") // JVM args for dynamic agent loading
}

tasks.test {
    // Configure JVM arguments for testing tasks
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading") // Enable dynamic agent loading during tests

    // Specify the use of JUnit 5 platform for testing
    useJUnitPlatform()
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    // Set active Spring profile to "dev" when running the application
    systemProperty("spring.profiles.active", "dev")
}
