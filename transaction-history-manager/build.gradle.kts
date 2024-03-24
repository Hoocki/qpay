plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.qpay.spring.base")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}