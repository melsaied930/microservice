plugins {
    java
    id("org.springframework.boot") version "3.3.3" // Spring Boot plugin for bootstrapping
    id("io.spring.dependency-management") version "1.1.6" // Spring Dependency Management plugin
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22)) // Use Java toolchain version 22
    }
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}

springBoot {
    buildInfo() // Generate build information for Spring Boot Actuator's `/info` endpoint
}

repositories {
    mavenCentral() // Use Maven Central as the repository for dependencies
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-web") // Starter for building web applications
    implementation("org.springframework.boot:spring-boot-starter-actuator") // Actuator for monitoring and management

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
    testImplementation("com.fasterxml.jackson.core:jackson-databind") // Jackson databind for JSON processing
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.17.0") // Embedded MongoDB for testing

    // OpenAPI / Swagger Support
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0") // OpenAPI UI for Spring Web MVC
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0") // OpenAPI UI for Spring WebFlux

    // Logging
    testImplementation("ch.qos.logback:logback-classic") // Logback classic for testing
    // https://mvnrepository.com/artifact/junit/junit
    testImplementation("junit:junit:4.13.2")

}

tasks.withType<Test> {
    useJUnitPlatform() // Use JUnit Platform for running tests
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading") // JVM args for dynamic agent loading
    dependsOn("prepareTestLogDir")
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading") // JVM args for dynamic agent loading
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:deprecation")) // Enable deprecation warnings
//    options.compilerArgs.add("-Xlint:deprecation")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    systemProperty("spring.profiles.active", "dev") // Set active Spring profile to "dev"
}

tasks.register<Copy>("prepareTestLogDir") {
    doFirst {
        file("build/test-logs").mkdirs() // Prepare test log directory
    }
}

tasks.test {
    useJUnitPlatform()
    jvmArgs = listOf("-Xshare:off")
}

//tasks.test {
//    // Configure JVM arguments for testing tasks
//    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading") // Enable dynamic agent loading during tests
//
//    // Specify the use of JUnit 5 platform for testing
//    useJUnitPlatform()
//}