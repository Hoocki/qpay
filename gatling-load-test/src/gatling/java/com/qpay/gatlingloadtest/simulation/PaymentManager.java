package com.qpay.gatlingloadtest.simulation;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
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
            )
            .exec(http("make payment request 4")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer4@gmail.com\", \"walletIdFrom\": 8, \"emailTo\": \"testMerchant@gmail.com\", \"walletIdTo\": 15, \"amount\": 1}"))
            )
            .exec(http("make payment request 5")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer5@gmail.com\", \"walletIdFrom\": 10, \"emailTo\": \"merchant1@gmail.com\", \"walletIdTo\": 11, \"amount\": 1}"))
            )
            .exec(http("make payment request 6")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer6@gmail.com\", \"walletIdFrom\": 16, \"emailTo\": \"merchant2@gmail.com\", \"walletIdTo\": 12, \"amount\": 1}"))
            )
            .exec(http("make payment request 7")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer7@gmail.com\", \"walletIdFrom\": 17, \"emailTo\": \"merchant3@gmail.com\", \"walletIdTo\": 13, \"amount\": 1}"))
            )
            .exec(http("make payment request 8")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer8@gmail.com\", \"walletIdFrom\": 18, \"emailTo\": \"testMerchant@gmail.com\", \"walletIdTo\": 15, \"amount\": 1}"))
            )
            .exec(http("make payment request 9")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer9@gmail.com\", \"walletIdFrom\": 19, \"emailTo\": \"merchant1@gmail.com\", \"walletIdTo\": 11, \"amount\": 1}"))
            )
            .exec(http("make payment request 10")
                    .post("/api/v1/payments/p2b")
                    .headers(Map.of("Content-Type", "application/json", "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjFAZ21haWwuY29tIiwiaWF0IjoxNzExNTE4Mjc3fQ.vbbLWpOiCXVE62ZrexYT5OnzqKsEq6eNsZ0PONh_IJo"))
                    .body(StringBody("{ \"emailFrom\": \"customer10@gmail.com\", \"walletIdFrom\": 20, \"emailTo\": \"merchant2@gmail.com\", \"walletIdTo\": 12, \"amount\": 1}"))
            );

    {
        setUp(
                scn.injectOpen(
                        constantUsersPerSec(1).during(Duration.of(30, SECONDS))

                )
        ).protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lte(1000),
                        global().successfulRequests().percent().gte(99.999)
                );
    }
}