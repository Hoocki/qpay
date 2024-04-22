package com.qpay.authmanager.service.jwt;

import com.qpay.libs.models.UserType;

public interface JwtService {

    String generateToken(String email, long userId, UserType userType);

    String extractEmail(String token);

    boolean validateToken(String token, String email);
}
