package com.qpay.usermanager.model.dto.authuser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import com.qpay.libs.models.UserType;

@Builder
public record UserCredentialsCreation(

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "UserType is required")
        UserType userType,

        @NotNull(message = "UserId couldn't be null")
        long userId
) {
}
