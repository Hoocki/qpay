package com.qpay.authmanager.service.authentication.impl;

import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.service.authentication.AuthenticationService;
import com.qpay.authmanager.service.jwt.JwtService;
import com.qpay.authmanager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final UserService userService;

    public JwtAuthenticationResponse signIn(final AuthCredentials authCredentials) {
        final var userEntity = userService.getUserByEmail(authCredentials.email());
        return jwtService.generate(userEntity);
    }

}
