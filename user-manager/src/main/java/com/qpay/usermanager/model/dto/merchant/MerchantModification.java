package com.qpay.usermanager.model.dto.merchant;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MerchantModification(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email,

        @Nullable
        String password

) {
}
