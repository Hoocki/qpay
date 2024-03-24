plugins {
	id("io.freefair.lombok") version "8.4" apply false
}

group = "com.qpay"
version = "0.0.1-SNAPSHOT"

tasks.wrapper {
	gradleVersion = "8.6"
	distributionType = Wrapper.DistributionType.ALL
}