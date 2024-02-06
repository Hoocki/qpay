package com.qpay.paymentmanager.client;

import com.qpay.paymentmanager.config.WebClientConfig;
import com.qpay.paymentmanager.model.dto.PaymentTransaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class TransactionHistoryClient {

    private final WebClient webClient;

    public static final String TRANSACTION_HISTORY_PATH = "/api/v1/history";

    public TransactionHistoryClient(@Qualifier(WebClientConfig.TRANSACTION_HISTORY_WEB_CLIENT_NAME) final WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<Void> saveTransactionToHistory(final PaymentTransaction paymentTransaction) {
        return webClient
                .post()
                .uri(TRANSACTION_HISTORY_PATH)
                .bodyValue(paymentTransaction)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
