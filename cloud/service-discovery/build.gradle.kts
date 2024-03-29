plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.qpay.java.base")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}