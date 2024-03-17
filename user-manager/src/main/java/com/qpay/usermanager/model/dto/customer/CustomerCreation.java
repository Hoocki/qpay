package com.qpay.usermanager.model.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerCreation(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password

) {
}