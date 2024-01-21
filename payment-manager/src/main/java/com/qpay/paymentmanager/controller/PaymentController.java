package com.qpay.paymentmanager.controller;

import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.PaymentService;
import com.qpay.paymentmanager.utils.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.PAYMENT_PATH)
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/p2b")
    public WalletEntity makePayment(@Valid @RequestBody final WalletPayment walletPayment) {
        return paymentService.makePayment(walletPayment);
    }

    @PostMapping("/topUp/{id}")
    public WalletEntity topUp(@PathVariable final long id,
                              @RequestBody final WalletTopUp walletTopUp) {
        return paymentService.topUp(walletTopUp, id);
    }

}
