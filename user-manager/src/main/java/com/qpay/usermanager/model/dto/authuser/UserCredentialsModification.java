package com.qpay.usermanager.model.dto.authuser;

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
