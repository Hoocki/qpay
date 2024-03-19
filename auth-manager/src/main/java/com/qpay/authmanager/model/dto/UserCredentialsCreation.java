package com.qpay.authmanager.model.dto;

import com.qpay.libs.models.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserCredentialsCreation(

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotNull
        UserType userType
) {
}

