group = "com.qpay"
version = "0.0.1-SNAPSHOT"

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
	}
}