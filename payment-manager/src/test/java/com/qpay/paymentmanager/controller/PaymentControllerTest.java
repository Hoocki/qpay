package com.qpay.paymentmanager.controller;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    private static final WalletEntity WALLET_ENTITY = WalletEntity
            .builder()
            .name("wallet")
            .balance(new BigDecimal(0))
            .userId(1L)
            .userType(UserType.CUSTOMER)
            .build();

    @Test
    void should_makePaymentBetweenWallets() {
        // given
        var walletPayment = WalletPayment.builder()
                .walletIdFrom(1L)
                .walletIdTo(2L)
                .amount(BigDecimal.valueOf(100))
                .build();

        given(paymentService.makePayment(walletPayment)).willReturn(WALLET_ENTITY);

        // when
        var result = paymentController.makePayment(walletPayment);

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

        given(paymentService.topUp(walletTopUp, id)).willReturn(expectedWallet);

        // when
        var result = paymentController.topUp(id, walletTopUp);

        // then
        assertThat(result).isEqualTo(expectedWallet);
    }
}