package com.qpay.paymentmanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WalletMapperTest {

    private final WalletMapper walletMapper = new WalletMapper();

    @Test
    void should_returnWalletEntity_when_walletModificationPassed() {
        // given
        var walletModification = WalletModification.builder()
                .name("wallet")
                .balance(new BigDecimal(0))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        //when
        var result = walletMapper.map(walletModification);

        //then
        var expectedWalletEntity = WalletEntity.builder()
                .name("wallet")
                .balance(new BigDecimal(0))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        assertThat(result).isEqualTo(expectedWalletEntity);
    }
}