package com.qpay.paymentmanager.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletPayment(

        @NotBlank(message = "Email must be not empty")
        @Email(message = "Invalid email format for emailFrom")
        String emailFrom,

        long walletIdFrom,

        @NotBlank(message = "Email must be not empty")
        @Email(message = "Invalid email format for emailTo")
        String emailTo,

        long walletIdTo,

        BigDecimal amount,

        boolean sendNotification
) {

}
