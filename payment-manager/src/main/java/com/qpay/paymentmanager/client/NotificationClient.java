package com.qpay.paymentmanager.client;

import com.qpay.paymentmanager.config.WebClientConfig;
import com.qpay.paymentmanager.model.dto.PaymentNotification;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationClient {

    public static final String NOTIFICATION_PATH = "/api/v1/notifications";

    private final WebClient webClient;

    public NotificationClient(@Qualifier(WebClientConfig.NOTIFICATION_WEB_CLIENT_NAME) final WebClient notificationWebClient) {
        this.webClient = notificationWebClient;
    }

    public ResponseEntity<Void> sendNotification(final WalletPayment walletPayment) {
        final var notificationCreation = PaymentNotification.builder()
                .amount(walletPayment.amount())
                .emailFrom(walletPayment.emailFrom())
                .emailTo(walletPayment.emailTo())
                .build();
        return webClient
                .post()
                .uri(NOTIFICATION_PATH)
                .bodyValue(notificationCreation)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
