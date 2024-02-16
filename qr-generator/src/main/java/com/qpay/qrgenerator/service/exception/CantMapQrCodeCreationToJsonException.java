package com.qpay.qrgenerator.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CantMapQrCodeCreationToJsonException extends RuntimeException {

    public CantMapQrCodeCreationToJsonException(final String message) {
        super(message);
    }
}
