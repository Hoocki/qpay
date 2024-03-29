plugins {
	id("com.qpay.spring.base")
}

dependencies {
	implementation(project(":libs"))
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("io.jsonwebtoken:jjwt-api:${Versions.JSON_WEBTOKEN}")

	runtimeOnly("io.jsonwebtoken:jjwt-impl:${Versions.JJWT_IMPL}")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Versions.JJWT_JACKSON}")
}