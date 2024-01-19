package com.qpay.paymentmanager.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.qpay.paymentmanager.model.dto.TransactionCreation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.qpay.paymentmanager.client.TransactionHistoryClient.TRANSACTION_HISTORY_PATH;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WireMockTest(httpPort = 8184)
class TransactionHistoryClientIntegrationTest {

    @Autowired
    private TransactionHistoryClient transactionHistoryClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_returnOK_when_sentTransactionToHistory() throws JsonProcessingException {
        // given
        var transaction = TransactionCreation
                .builder()
                .nameFrom("bob")
                .nameTo("tom")
                .idFrom(1)
                .idTo(2)
                .amount(new BigDecimal(15))
                .build();

        // when
        stubFor(post(urlPathEqualTo(TRANSACTION_HISTORY_PATH))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(transaction)))
                .willReturn(ok()));

        var res = transactionHistoryClient.saveTransactionToHistory(transaction).getStatusCode();

        // then
        assertThat(res).isEqualTo(HttpStatus.OK);
    }
}