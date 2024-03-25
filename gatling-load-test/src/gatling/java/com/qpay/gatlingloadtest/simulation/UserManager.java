package com.qpay.gatlingloadtest.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
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
                    .headers(Map.of("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMzNAZ21haWwuY29tIiwiaWF0IjoxNzExMzU5NzE2fQ.toUjnetk8WCZB_CyKZKrMs4BITFzJBewzV6EFLpN3iE"))
            );

    {
        setUp(
                scn.injectOpen(
                        rampUsersPerSec(0).to(200).during(Duration.of(10, SECONDS)),
                        rampUsers(230).during(Duration.of(30, SECONDS))
                )
        ).protocols(httpProtocol)
                .assertions(
                        global().successfulRequests().percent().gte(99.999)
                );
    }
}