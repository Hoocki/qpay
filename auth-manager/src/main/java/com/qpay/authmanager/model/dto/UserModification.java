package com.qpay.authmanager.model.dto;

import com.qpay.libs.models.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserModification (

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        UserType userType
) {
}
