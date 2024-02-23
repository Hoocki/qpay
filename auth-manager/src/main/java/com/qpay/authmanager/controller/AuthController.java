package com.qpay.authmanager.controller;

import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.service.authentication.AuthenticationService;
import com.qpay.authmanager.utility.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.AUTH_PATH)
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public JwtAuthenticationResponse signIn(@RequestBody final AuthCredentials authCredentials) {
        return authenticationService.signIn(authCredentials);
    }

    @GetMapping("/validate")
    public void validateToken() {
    }
}
