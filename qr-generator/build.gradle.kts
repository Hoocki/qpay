plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	id("com.qpay.spring.base")
}

dependencies {
	implementation("com.google.zxing:core:${Versions.ZXING_CORE}")
	implementation("com.google.zxing:javase:${Versions.ZXING_JAVASE}")
	implementation("org.springframework.boot:spring-boot-starter-validation")
}
