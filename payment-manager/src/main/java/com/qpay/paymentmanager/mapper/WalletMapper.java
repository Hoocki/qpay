package com.qpay.paymentmanager.mapper;

import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public WalletEntity map(final WalletModification walletModification) {
        return WalletEntity.builder()
                .name(walletModification.name())
                .balance(walletModification.balance())
                .userId(walletModification.userId())
                .userType(walletModification.userType())
                .build();
    }
}
