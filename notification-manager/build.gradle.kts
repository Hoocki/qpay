plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.qpay.java.tests")
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
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka:${Versions.KAFKA}")

	compileOnly("org.projectlombok:lombok")

}