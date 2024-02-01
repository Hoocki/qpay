plugins {
    java
    id("io.freefair.lombok") version "8.4"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val jakartaValidation = rootProject.extra["jakartaValidation"]

dependencies {

    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidation")

}