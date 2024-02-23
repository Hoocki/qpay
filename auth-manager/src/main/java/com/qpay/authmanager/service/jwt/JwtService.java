package com.qpay.authmanager.service.jwt;

import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.entity.UserEntity;

public interface JwtService {

    JwtAuthenticationResponse generate(UserEntity user);

    String extractEmail(String token);

    boolean isValid(String token, String email);
}
