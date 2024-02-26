plugins {
    java
    id("io.freefair.lombok")
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

    implementation("jakarta.validation:jakarta.validation-api:${Versions.JAKARTA_VALIDATION}")

}