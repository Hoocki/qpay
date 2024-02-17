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

val kafkaVersion = rootProject.extra["kafka"]

dependencies {
	annotationProcessor("org.projectlombok:lombok")

	implementation(project(":libs"))
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka:$kafkaVersion")

	compileOnly("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
