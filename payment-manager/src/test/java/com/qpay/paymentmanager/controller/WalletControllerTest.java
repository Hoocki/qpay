package com.qpay.paymentmanager.controller;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @InjectMocks
    private WalletController walletController;

    @Mock
    private WalletService walletService;

    private static final WalletCreation WALLET_CREATION = WalletCreation.builder().name("wallet").userId(1L).userType(UserType.CUSTOMER).build();

    private static final WalletModification WALLET_MODIFICATION = WalletModification.builder().name("wallet").build();

    private static final WalletEntity WALLET_ENTITY = WalletEntity.builder().name("wallet").balance(new BigDecimal(0)).userId(1L).userType(UserType.CUSTOMER).build();

    @Test
    void should_returnWallet() {
        // given
        given(walletService.getWalletById(1L)).willReturn(WALLET_ENTITY);

        // when
        var result = walletController.getWalletById(1L);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_addWallet() {
        // given
        given(walletService.addWallet(WALLET_CREATION)).willReturn(WALLET_ENTITY);

        // when
        var result = walletController.addWallet(WALLET_CREATION);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_updateWallet() {
        // given
        given(walletService.updateWallet(WALLET_MODIFICATION, 1L)).willReturn(WALLET_ENTITY);

        // when
        var result = walletController.updateWallet(WALLET_MODIFICATION, 1L);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_deleteWallet() {
        // given
        var id = 1L;

        // when
        walletController.deleteWallet(id);

        // then
        then(walletService).should().deleteWallet(id);
    }

    @Test
    void should_makePaymentBetweenWallets() {
        // given
        var walletPayment = WalletPayment.builder()
                .walletIdFrom(1L)
                .walletIdTo(2L)
                .amount(BigDecimal.valueOf(100))
                .build();

        given(walletService.makePayment(walletPayment)).willReturn(WALLET_ENTITY);

        // when
        var result = walletController.paymentWallet(walletPayment);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_topUpWallet() {
        // given
        var id = 1L;
        var walletTopUp = WalletTopUp.builder()
                .amount(BigDecimal.valueOf(100))
                .build();

        var expectedWallet = WalletEntity.builder()
                .name("wallet")
                .balance(new BigDecimal(100))
                .userId(id)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletService.topUp(walletTopUp, id)).willReturn(expectedWallet);

        // when
        var result = walletController.topUpWallet(id, walletTopUp);

        // then
        assertThat(result).isEqualTo(expectedWallet);
    }
}