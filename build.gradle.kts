plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // JPA and Hibernate for relational database support
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.hibernate.orm:hibernate-core:6.2.8.Final")  // Hibernate ORM

    // MongoDB support
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // Lombok for code simplification
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Development tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Embedded MongoDB for testing
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.17.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-XX:+EnableDynamicAgentLoading")
}
