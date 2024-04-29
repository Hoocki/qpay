package com.qpay.paymentmanager.model.dto;

import com.qpay.libs.models.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentTransaction(

   String nameFrom,

   String nameTo,

   long idFrom,

   long idTo,

   BigDecimal amount,

   TransactionType transactionType
) {
}
