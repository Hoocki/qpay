package com.qpay.paymentmanager.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.qpay.libs.models.PaymentNotification;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.qpay.paymentmanager.client.NotificationClient.NOTIFICATION_PATH;

@Disabled
@SpringBootTest
@WireMockTest(httpPort = 8183)
class NotificationClientIntegrationTest {

    @Autowired
    private NotificationClient notificationClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_returnOK_whenSendNotificationSuccessfully() throws JsonProcessingException {
        // given
        var paymentNotification = PaymentNotification
                .builder()
                .amount(BigDecimal.valueOf(100))
                .emailFrom("example1@gmail.com")
                .emailTo("example2@gmail.com")
                .build();

        var walletPayment = WalletPayment
                .builder()
                .emailFrom("example1@gmail.com")
                .walletIdFrom(1L)
                .emailTo("example2@gmail.com")
                .walletIdTo(2L)
                .amount(BigDecimal.valueOf(100))
                .build();

        var notificationCreation = PaymentNotification.builder()
                .amount(walletPayment.amount())
                .emailFrom(walletPayment.emailFrom())
                .emailTo(walletPayment.emailTo())
                .build();


        // when
        stubFor(post(urlPathEqualTo(NOTIFICATION_PATH))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(paymentNotification)))
                .willReturn(ok()));

        notificationClient.sendNotification(notificationCreation);
    }
}