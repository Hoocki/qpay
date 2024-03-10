package com.qpay.authmanager.service.authentication;

import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signIn(AuthCredentials authCredentials);

}
