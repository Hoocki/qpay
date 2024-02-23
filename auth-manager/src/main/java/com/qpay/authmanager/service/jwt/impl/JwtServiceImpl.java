package com.qpay.authmanager.service.jwt.impl;

import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    public JwtAuthenticationResponse generate(final UserEntity user) {
        return JwtAuthenticationResponse
                .builder()
                .token(user.toString())
                .build();
    }

    public String extractEmail(final String token) {
        return token;
    }

    public boolean isValid(final String token, final String email) {
        return false;
    }
}