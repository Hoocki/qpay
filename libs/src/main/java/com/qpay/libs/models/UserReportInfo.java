package com.qpay.libs.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserReportInfo(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        String email

) {
}
