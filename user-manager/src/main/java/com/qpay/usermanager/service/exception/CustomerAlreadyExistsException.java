package com.qpay.usermanager.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException() {
        super("Customer already exists with this email");
    }
    public CustomerAlreadyExistsException(final String message) {
        super(message);
    }
}
