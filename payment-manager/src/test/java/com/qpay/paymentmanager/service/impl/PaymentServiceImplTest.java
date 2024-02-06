package com.qpay.paymentmanager.service.impl;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.client.TransactionHistoryClient;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.repository.WalletRepository;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.service.exception.NoWalletFoundException;
import com.qpay.paymentmanager.service.exception.NotEnoughMoneyWalletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private WalletService walletService;

    @Mock
    private ExecutorService executorService;

    @Mock
    private TransactionHistoryClient transactionHistoryClient;

    private static final WalletEntity WALLET_ENTITY = WalletEntity
            .builder()
            .name("wallet")
            .balance(new BigDecimal(0))
            .userId(1L)
            .userType(UserType.CUSTOMER)
            .build();

    @Test
    void should_makePaymentBetweenWallets() {
        // when
        var fromId = 1L;
        var toId = 2L;
        var walletPayment = WalletPayment.builder()
                .walletIdFrom(fromId)
                .walletIdTo(toId)
                .amount(BigDecimal.valueOf(100))
                .build();

        var fromWallet = WalletEntity.builder()
                .id(fromId)
                .name("CustomerWallet")
                .balance(new BigDecimal(200))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        var toWallet = WalletEntity.builder()
                .id(toId)
                .name("MerchantWallet")
                .balance(new BigDecimal(300))
                .userId(2L)
                .userType(UserType.CUSTOMER)
                .build();

        var expectedFromWallet = WalletEntity.builder()
                .id(fromId)
                .name("CustomerWallet")
                .balance(new BigDecimal(100))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        var expectedToWallet = WalletEntity.builder()
                .id(toId)
                .name("MerchantWallet")
                .balance(new BigDecimal(400))
                .userId(2L)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletService.getWalletById(fromId)).willReturn(fromWallet);
        given(walletService.getWalletById(toId)).willReturn(toWallet);
        given(walletRepository.save(expectedFromWallet)).willReturn(expectedFromWallet);
        given(walletRepository.save(expectedToWallet)).willReturn(expectedToWallet);
        willDoNothing().given(executorService).execute(any());

        // when
        var result = paymentService.makePayment(walletPayment);

        // then
        assertThat(result).isEqualTo(expectedFromWallet);
    }

    @Test
    void should_throwNoWalletFoundException_when_walletDoesntExistWithIdFrom() {
        // when
        var fromId = 999L;
        var toId = 2L;
        var walletPayment = WalletPayment.builder()
                .walletIdFrom(fromId)
                .walletIdTo(toId)
                .amount(BigDecimal.valueOf(100))
                .build();

        given(walletService.getWalletById(fromId)).willThrow(NoWalletFoundException.class);

        // when
        var thrown = catchThrowable(() -> paymentService.makePayment(walletPayment));

        // then
        assertThat(thrown).isInstanceOf(NoWalletFoundException.class);
    }

    @Test
    void should_throwNotEnoughMoneyException_when_balanceOfWalletIsLowerThanZero() {
        // when
        var fromId = 999L;
        var toId = 2L;
        var walletPayment = WalletPayment.builder()
                .walletIdFrom(fromId)
                .walletIdTo(toId)
                .amount(BigDecimal.valueOf(201))
                .build();

        var fromWallet = WalletEntity.builder()
                .id(fromId)
                .name("CustomerWallet")
                .balance(new BigDecimal(200))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        var toWallet = WalletEntity.builder()
                .id(toId)
                .name("MerchantWallet")
                .balance(new BigDecimal(300))
                .userId(2L)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletService.getWalletById(fromId)).willReturn(fromWallet);
        given(walletService.getWalletById(toId)).willReturn(toWallet);

        // when
        var thrown = catchThrowable(() -> paymentService.makePayment(walletPayment));

        // then
        assertThat(thrown).isInstanceOf(NotEnoughMoneyWalletException.class);
    }

    @Test
    void should_topUpBalanceInWallet() {
        // when
        var id = 1L;
        var walletTopUp = WalletTopUp.builder()
                .amount(BigDecimal.valueOf(100))
                .build();

        var expectedWallet = WalletEntity.builder()
                .name("wallet")
                .balance(new BigDecimal(100))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletService.getWalletById(id)).willReturn(WALLET_ENTITY);
        given(walletRepository.save(expectedWallet)).willReturn(expectedWallet);

        // when
        var result = paymentService.topUp(walletTopUp, id);

        // then
        assertThat(result).isEqualTo(expectedWallet);
    }

    @Test
    void should_throwNotEnoughMoneyWalletException_when_topUpBalanceOfWalletIsLowerThanZero() {
        // when
        var id = 1L;
        var walletTopUp = WalletTopUp.builder()
                .amount(BigDecimal.valueOf(-100))
                .build();

        // when
        var thrown = catchThrowable(() -> paymentService.topUp(walletTopUp, id));

        // then
        assertThat(thrown).isInstanceOf(NotEnoughMoneyWalletException.class);
    }

    @Test
    void should_throwNoWalletFoundException_when_walletDoesntExist() {
        // when
        var id = 999L;
        var walletTopUp = WalletTopUp.builder()
                .amount(BigDecimal.valueOf(100))
                .build();

        given(walletService.getWalletById(id)).willThrow(NoWalletFoundException.class);

        // when
        var thrown = catchThrowable(() -> paymentService.topUp(walletTopUp, id));

        // then
        assertThat(thrown).isInstanceOf(NoWalletFoundException.class);
    }
}