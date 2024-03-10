package com.qpay.authmanager.service.authentication.impl;

import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.service.authentication.AuthenticationService;
import com.qpay.authmanager.service.jwt.JwtService;
import com.qpay.authmanager.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signIn(final AuthCredentials authCredentials) {
        final var userEntity = userService.getUserByEmail(authCredentials.email());
        final var generatedToken = jwtService.generateToken(userEntity.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authCredentials.email(), authCredentials.password()));
        return new JwtAuthenticationResponse(generatedToken);
    }

}
