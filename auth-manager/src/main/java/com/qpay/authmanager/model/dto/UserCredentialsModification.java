package com.qpay.authmanager.model.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserCredentialsModification(

        @NotBlank(message = "Email is required")
        String email,

        @Nullable
        String password
) {
}
