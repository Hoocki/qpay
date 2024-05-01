package com.qpay.paymentmanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record WalletModification (
        @NotBlank(message = "Name is required")
        String name

) {
}
