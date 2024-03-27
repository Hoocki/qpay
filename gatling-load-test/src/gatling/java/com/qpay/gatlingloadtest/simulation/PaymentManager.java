package com.qpay.gatlingloadtest.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static java.time.temporal.ChronoUnit.SECONDS;

public class PaymentManager extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080/")
            .acceptHeader("application/json");

    ScenarioBuilder scn = scenario("Make payments between customers and merchants")
            .exec(http("make payment request 1")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer1@gmail.com\", \"walletIdFrom\": 5, \"emailTo\": \"merchant1@gmail.com\", \"walletIdTo\": 11, \"amount\": 1}"))
            )
            .exec(http("make payment request 2")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer2@gmail.com\", \"walletIdFrom\": 6, \"emailTo\": \"merchant2@gmail.com\", \"walletIdTo\": 12, \"amount\": 1}"))
            )
            .exec(http("make payment request 3")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer3@gmail.com\", \"walletIdFrom\": 7, \"emailTo\": \"merchant3@gmail.com\", \"walletIdTo\": 13, \"amount\": 1}"))
            );


    {
        setUp(
                scn.injectOpen(
                        rampUsers(40).during(Duration.of(10, SECONDS)),
                        constantUsersPerSec(5).during(Duration.of(30, SECONDS))

                )
        ).protocols(httpProtocol)
                .assertions(
                        global().successfulRequests().percent().gte(99.999)
                );
    }
}