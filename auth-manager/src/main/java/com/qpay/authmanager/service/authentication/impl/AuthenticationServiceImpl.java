package com.qpay.authmanager.service.authentication.impl;

import com.qpay.authmanager.mapper.TokenDataMapper;
import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.service.authentication.AuthenticationService;
import com.qpay.authmanager.service.jwt.JwtService;
import com.qpay.authmanager.service.user.UserCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;

    private final UserCredentialsService userCredentialsService;

    private final AuthenticationManager authenticationManager;

    private final TokenDataMapper tokenDataMapper;

    public JwtAuthenticationResponse signIn(final AuthCredentials authCredentials) {
        final var userEntity = userCredentialsService.getUserByEmail(authCredentials.email());
        final var tokenData = tokenDataMapper.map(userEntity);
        final var generatedToken = jwtService.generateToken(tokenData);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authCredentials.email(), authCredentials.password()));
        return new JwtAuthenticationResponse(generatedToken);
    }

}
