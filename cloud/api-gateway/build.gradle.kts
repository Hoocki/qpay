plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.qpay.java.base")
}

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-gateway")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j:${Versions.CIRCUIT_BREAKER}")
	implementation("org.springframework.boot:spring-boot-starter-actuator:${Versions.ACTUATOR}")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
	}
}