package com.qpay.authmanager.service.jwt;

public interface JwtService {

    String generateToken(String email);

    String extractEmail(String token);

    boolean validateToken(String token, String email);
}
