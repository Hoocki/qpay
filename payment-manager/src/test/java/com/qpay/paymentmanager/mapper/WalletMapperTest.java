package com.qpay.paymentmanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
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

    @Test
    void should_returnWalletPayment_when_walletTopUp_and_walletEntityPassed() {
        // given
        var walletTopUp = WalletTopUp.builder()
                .amount(BigDecimal.valueOf(10))
                .email("user@mail.ru")
                .sendNotification(false)
                .build();

        var walletEntity = WalletEntity.builder()
                .id(1L)
                .name("wallet")
                .balance(new BigDecimal(0))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        //when
        var result = walletMapper.map(walletTopUp, walletEntity);

        //then
        var expectedWalletPayment = WalletPayment.builder()
                .walletIdTo(1L)
                .walletIdFrom(1L)
                .emailTo("user@mail.ru")
                .emailFrom("user@mail.ru")
                .amount(BigDecimal.valueOf(10))
                .sendNotification(false)
                .build();

        assertThat(result).isEqualTo(expectedWalletPayment);
    }
}