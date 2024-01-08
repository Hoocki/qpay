package com.qpay.paymentmanager.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyWalletException extends RuntimeException {

    public NotEnoughMoneyWalletException() {
        super("Not enough money in the balance");
    }
}
