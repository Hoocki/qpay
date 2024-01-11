package com.qpay.paymentmanager.service;

import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;

public interface PaymentService {

    WalletEntity makePayment(WalletPayment walletPayment);

    WalletEntity topUp(WalletTopUp walletTopUp, long id);
}
