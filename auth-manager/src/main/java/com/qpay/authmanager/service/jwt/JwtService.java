package com.qpay.authmanager.service.jwt;

import com.qpay.authmanager.model.dto.TokenData;

public interface JwtService {

    String generateToken(TokenData tokenData);

    String extractEmail(String token);

    boolean validateToken(String token, String email);
}
