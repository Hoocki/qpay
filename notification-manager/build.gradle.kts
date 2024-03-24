plugins {
	id("com.qpay.spring.base")
}

dependencies {
	implementation(project(":libs"))
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.kafka:spring-kafka:${Versions.KAFKA}")
}