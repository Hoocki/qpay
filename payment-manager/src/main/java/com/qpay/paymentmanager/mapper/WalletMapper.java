package com.qpay.paymentmanager.mapper;

import com.qpay.paymentmanager.model.dto.WalletCreation;
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
}
