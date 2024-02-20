rootProject.name = "qpay"

include(
        "user-manager",
        "payment-manager",
        "qr-generator",
        "notification-manager",
        "transaction-history-manager",
        ":libs"
)