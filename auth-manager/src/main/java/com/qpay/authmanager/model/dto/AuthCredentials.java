package com.qpay.authmanager.model.dto;

import lombok.Builder;

@Builder
public record AuthCredentials (String email, String password) {
}
