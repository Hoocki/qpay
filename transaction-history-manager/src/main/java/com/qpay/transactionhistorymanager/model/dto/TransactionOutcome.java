package com.qpay.transactionhistorymanager.model.dto;
import lombok.Builder;

@Builder
public record TransactionOutcome (
        Outcome expenses,
        Outcome income
) {
}
