package com.qpay.usermanager.model.dto.wallet;

import com.qpay.libs.models.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record WalletCreation(
        @NotBlank(message = "Name is required")
        String name,

        long userId,

        @NotNull(message = "UserType is required")
        UserType userType

) {
}