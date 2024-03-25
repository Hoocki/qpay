plugins {
    id("com.qpay.spring.base")
}

dependencies {
    implementation(project(":libs"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${Versions.OPENFEIGN}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core:${Versions.FLYWAY}")
    implementation("org.postgresql:postgresql:${Versions.POSTGRESQL}")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}