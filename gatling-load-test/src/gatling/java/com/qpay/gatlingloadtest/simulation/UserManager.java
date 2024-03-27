package com.qpay.gatlingloadtest.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static java.time.temporal.ChronoUnit.SECONDS;

public class UserManager extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080/")
            .acceptHeader("application/json");

    ScenarioBuilder scn = scenario("Getting users")
            .exec(http("get customers request")
                    .get("/api/v1/customers/26")
                    .headers(Map.of("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
            );

    {
        setUp(
                scn.injectOpen(
                        rampUsers(1000).during(Duration.of(10, SECONDS)),
                        constantUsersPerSec(230).during(Duration.of(30, SECONDS))
                )
        ).protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lte(1000),
                        global().successfulRequests().percent().gte(99.999)
                );
    }
}