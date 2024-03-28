plugins {
    id("java")
    id("io.gatling.gradle") version "3.10.5"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("io.gatling:gatling-app:${Versions.GATLING_APP}")
    implementation("io.gatling.highcharts:gatling-charts-highcharts:${Versions.GATLING_CHARTS}")
}