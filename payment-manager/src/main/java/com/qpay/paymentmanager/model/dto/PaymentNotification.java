package com.qpay.paymentmanager.model.dto;


import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentNotification(
        BigDecimal amount,

        String emailFrom,

        String emailTo
) {
}
