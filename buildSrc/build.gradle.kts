plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
}

val kotlinVersion = "1.9.22"
val dependencyPluginVersion = "1.1.4"
val gradlePluginVersion = "3.2.4"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("io.spring.gradle:dependency-management-plugin:$dependencyPluginVersion")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$gradlePluginVersion")
}