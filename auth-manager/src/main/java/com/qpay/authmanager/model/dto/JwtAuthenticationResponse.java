package com.qpay.authmanager.model.dto;

import lombok.Builder;

@Builder
public record JwtAuthenticationResponse (String token) {
}
