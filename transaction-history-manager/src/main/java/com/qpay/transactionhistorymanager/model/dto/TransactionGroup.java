package com.qpay.transactionhistorymanager.model.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionGroup(
        BigDecimal amount,
        String destination,
        BigDecimal share
) {
}
