pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "qpay"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
            mavenCentral()
    }
}

include(
        "user-manager",
        "payment-manager",
        "qr-generator",
        "notification-manager",
        ":libs"
)