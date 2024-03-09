plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.qpay.java.base")
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
    implementation(project(":libs"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-core:${Versions.FLYWAY}")
    implementation("org.postgresql:postgresql:${Versions.POSTGRESQL}")
    implementation("org.springframework.boot:spring-boot-starter-webflux:${Versions.WEBFLUX}")
    implementation("org.springframework.kafka:spring-kafka:${Versions.KAFKA}")
}