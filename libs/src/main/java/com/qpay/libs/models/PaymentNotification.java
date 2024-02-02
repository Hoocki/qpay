package com.qpay.libs.models;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentNotification(
        BigDecimal amount,

        String emailFrom,

        String emailTo
) {
}
