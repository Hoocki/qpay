plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val flywayVersion = rootProject.extra["flyway"]
val postgresqlVersion = rootProject.extra["postgresql"]
val junitJupiterApiVersion = rootProject.extra["junit-jupiter-api"]
val h2Version = rootProject.extra["h2"]
val webFluxVersion = rootProject.extra["webFlux"]
val wireMockVersion = rootProject.extra["wireMock"]

dependencies {
    annotationProcessor("org.projectlombok:lombok")

    implementation(project(":libs"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("org.springframework.boot:spring-boot-starter-webflux:$webFluxVersion")

    compileOnly("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testImplementation("com.h2database:h2:$h2Version")
    testImplementation("org.wiremock:wiremock-standalone:$wireMockVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}