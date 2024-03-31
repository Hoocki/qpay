plugins {
    id("com.qpay.spring.base")
}

dependencies {
    implementation(project(":libs"))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.itextpdf:itext7-core:${Versions.I_TEXT_PDF}")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${Versions.OPENFEIGN}")

    testImplementation("org.wiremock:wiremock-standalone:${Versions.WIREMOCK}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}