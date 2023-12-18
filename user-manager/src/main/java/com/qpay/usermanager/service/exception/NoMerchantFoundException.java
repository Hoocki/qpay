package com.qpay.usermanager.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoMerchantFoundException extends RuntimeException {
    public NoMerchantFoundException(final String message) {
        super(message);
    }
}
