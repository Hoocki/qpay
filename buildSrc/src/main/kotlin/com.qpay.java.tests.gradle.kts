plugins {
    java
    idea
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
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
    integrationTestImplementation("com.h2database:h2:${Versions.H2}")
    integrationTestImplementation("org.wiremock:wiremock-standalone:${Versions.WIREMOCK}")
}