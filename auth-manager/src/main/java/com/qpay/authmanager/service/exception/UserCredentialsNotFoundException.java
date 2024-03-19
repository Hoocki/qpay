package com.qpay.authmanager.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserCredentialsNotFoundException extends RuntimeException{

    public UserCredentialsNotFoundException() {
        super("User doesn't exist with this email");
    }
}
