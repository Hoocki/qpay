plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.qpay.java.base")
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
	implementation("com.google.zxing:core:${Versions.ZXING_CORE}")
	implementation("com.google.zxing:javase:${Versions.ZXING_JAVASE}")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
}