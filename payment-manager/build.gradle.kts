plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")

    implementation(project(":libs"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-core:${Versions.FLYWAY}")
    implementation("org.postgresql:postgresql:${Versions.POSTGRESQL}")
    implementation("org.springframework.boot:spring-boot-starter-webflux:${Versions.WEBFLUX}")
    implementation("org.springframework.kafka:spring-kafka:${Versions.KAFKA}")

    compileOnly("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT_JUPITER_API}")
    testImplementation("com.h2database:h2:${Versions.H2}")
    testImplementation("org.wiremock:wiremock-standalone:${Versions.WIREMOCK}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}