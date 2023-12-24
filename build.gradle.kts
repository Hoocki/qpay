group = "com.qpay"

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
	}
}