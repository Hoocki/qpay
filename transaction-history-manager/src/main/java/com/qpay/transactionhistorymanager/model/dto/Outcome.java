package com.qpay.transactionhistorymanager.model.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record Outcome (
        BigDecimal total,
        List<TransactionGroup> transactionsGroup
) {
}
