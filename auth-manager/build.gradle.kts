plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.qpay.java.base")
}

dependencies {
	implementation(project(":libs"))
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.jsonwebtoken:jjwt-api:${Versions.JSON_WEBTOKEN}")

	runtimeOnly("io.jsonwebtoken:jjwt-impl:${Versions.JJWT_IMPL}")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Versions.JJWT_JACKSON}")
}