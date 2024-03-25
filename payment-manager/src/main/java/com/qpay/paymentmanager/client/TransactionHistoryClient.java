package com.qpay.paymentmanager.client;

import com.qpay.paymentmanager.model.dto.PaymentTransaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "transaction-history")
public interface TransactionHistoryClient {

    String TRANSACTION_HISTORY_PATH = "/api/v1/transactions";

    @PostMapping(value = TRANSACTION_HISTORY_PATH)
    void saveTransaction(PaymentTransaction paymentTransaction);

}
