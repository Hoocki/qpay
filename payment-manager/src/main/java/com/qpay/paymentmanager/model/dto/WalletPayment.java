package com.qpay.paymentmanager.model.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletPayment(
        long walletIdFrom,

        long walletIdTo,

        BigDecimal amount
) {

}
