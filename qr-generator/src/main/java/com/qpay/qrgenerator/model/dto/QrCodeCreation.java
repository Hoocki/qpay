package com.qpay.qrgenerator.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record QrCodeCreation (
        long walletId,

        @NotBlank(message = "Username is required")
        String name,

        @NotNull(message = "Money is required")
        BigDecimal money

) {
}
