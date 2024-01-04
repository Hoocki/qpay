plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.qpay"
version = "0.0.1-SNAPSHOT"

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

dependencies {
    annotationProcessor("org.projectlombok:lombok")

    implementation(project(":libs"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")

    compileOnly("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterApiVersion")
    testImplementation("com.h2database:h2:$h2Version")
}

tasks.withType<Test> {
    useJUnitPlatform()
}