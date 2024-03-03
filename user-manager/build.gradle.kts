plugins {
    java
    idea
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val integrationTaskName = "integrationTest"

sourceSets {
    create(integrationTaskName) {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val integrationTest = task<Test>(integrationTaskName) {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets[integrationTaskName].output.classesDirs
    classpath = sourceSets[integrationTaskName].runtimeClasspath
    shouldRunAfter("test")

    useJUnitPlatform()
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}
val integrationTestRuntimeOnly by configurations.getting

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

idea {
    module {
        testSources.from(sourceSets[integrationTaskName].java.srcDirs)
    }
}

tasks.check { dependsOn(integrationTest) }

dependencies {
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-core:${Versions.FLYWAY}")
    implementation("org.postgresql:postgresql:${Versions.POSTGRESQL}")

    compileOnly("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT_JUPITER_API}")
    testImplementation("com.h2database:h2:${Versions.H2}")

    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT_JUPITER_API}")
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationTestRuntimeOnly("com.h2database:h2:${Versions.H2}")
    integrationTestRuntimeOnly("org.junit.platform:junit-platform-launcher")

}