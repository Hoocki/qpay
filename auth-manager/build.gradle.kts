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

val tokenVersion = rootProject.extra["jsonwebtoken"]
val jjwtImplVersion = rootProject.extra["jjwt-impl"]
val jjwtJacksonVersion = rootProject.extra["jjwt-jackson"]

dependencies {
	annotationProcessor("org.projectlombok:lombok")

	implementation(project(":libs"))
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.jsonwebtoken:jjwt-api:$tokenVersion")

	compileOnly("org.projectlombok:lombok")

	runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtImplVersion")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtJacksonVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
