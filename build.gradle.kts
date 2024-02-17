group = "com.qpay"
version = "0.0.1-SNAPSHOT"

plugins {
	id("org.springframework.boot") version "3.2.2" apply false
	id("io.spring.dependency-management") version "1.1.4" apply false
	id("io.freefair.lombok") version "8.4" apply false
}

allprojects {
	repositories {
		mavenCentral()
	}
}

allprojects {
	ext {
		set("flyway", "9.22.2")
		set("postgresql", "42.6.0")
		set("junit-jupiter-api", "5.10.0")
		set("h2", "2.2.224")
		set("webFlux", "3.2.2")
		set("wireMock", "3.3.1")
		set("jakartaValidation", "3.0.0")
		set("kafka", "3.1.1")
		set("zxingCore", "3.5.3")
		set("zxingJavaSe", "3.5.3")
	}
}