package com.qpay.paymentmanager.mapper;

import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletEntity map(final WalletCreation walletCreation) {
        return WalletEntity.builder()
                .name(walletCreation.name())
                .userId(walletCreation.userId())
                .userType(walletCreation.userType())
                .build();
    }

    public WalletPayment map(final WalletTopUp walletTopUp, final WalletEntity walletEntity) {
        return WalletPayment.builder()
                .walletIdFrom(walletEntity.getId())
                .emailFrom(walletTopUp.email())
                .walletIdTo(walletEntity.getId())
                .emailTo(walletTopUp.email())
                .amount(walletTopUp.amount())
                .sendNotification(walletTopUp.sendNotification())
                .build();
    }
}
