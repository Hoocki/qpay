package com.qpay.paymentmanager.model.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletTopUp(
        BigDecimal amount,

        String email,

        boolean sendNotification
) {
}
