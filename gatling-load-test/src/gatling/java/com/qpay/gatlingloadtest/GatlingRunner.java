package com.qpay.gatlingloadtest;

import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;

public class GatlingRunner {

    public static void main(final String[] args) {
        final var props = new GatlingPropertiesBuilder()
                .resourcesDirectory("src/gatling/resources")
                .resultsDirectory("build/reports/gatling")
                .binariesDirectory("build/classes/java/gatling")
                .simulationClass("com.qpay.gatlingloadtest.simulation.UserSimulation");
        Gatling.fromMap(props.build());
    }

}