package com.qpay.paymentmanager.model.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentTransaction(

   String nameFrom,

   String nameTo,

   long idFrom,

   long idTo,

   BigDecimal amount
) {
}
