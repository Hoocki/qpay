package com.qpay.paymentmanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WalletMapperTest {

    private final WalletMapper walletMapper = new WalletMapper();

    @Test
    void should_returnWalletEntity_when_walletModificationPassed() {
        // given
        var walletCreation = WalletCreation.builder()
                .name("wallet")
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        //when
        var result = walletMapper.map(walletCreation);

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