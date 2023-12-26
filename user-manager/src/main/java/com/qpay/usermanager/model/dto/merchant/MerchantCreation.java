package com.qpay.usermanager.model.dto.merchant;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
public record MerchantCreation(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password

) {
}
