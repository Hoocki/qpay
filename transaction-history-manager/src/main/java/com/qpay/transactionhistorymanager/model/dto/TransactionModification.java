package com.qpay.transactionhistorymanager.model.dto;

import com.qpay.libs.models.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionModification(

        @NotEmpty(message = "nameFrom is required")
        String nameFrom,

        long idFrom,

        @NotEmpty(message = "nameTo is required")
        String nameTo,

        long idTo,

        @NotNull(message = "amount is required")
        BigDecimal amount,

        @NotNull(message = "transactionType is required")
        TransactionType transactionType
) {
}
