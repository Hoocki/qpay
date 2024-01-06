package com.qpay.paymentmanager.model.dto;

import com.qpay.libs.models.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletModification (
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Balance is required")
        BigDecimal balance,

        long userId,

        @NotNull(message = "UserType is required")
        UserType userType

) {
}
