package com.qpay.libs.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PdfReportInfo (

        long userId,

        @NotNull(message = "periodStart is required")
        LocalDateTime periodStart,

        @NotNull(message = "periodEnd is required")
        LocalDateTime periodEnd,

        @NotNull(message = "userType is required")
        UserType userType
) {
}
