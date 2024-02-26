plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "qpay"

include(
        "user-manager",
        "payment-manager",
        "qr-generator",
        "notification-manager",
        ":libs"
)