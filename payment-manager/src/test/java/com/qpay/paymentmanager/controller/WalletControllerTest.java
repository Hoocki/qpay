package com.qpay.paymentmanager.controller;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
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

    private static final WalletCreation WALLET_CREATION = WalletCreation
            .builder()
            .name("wallet")
            .userId(1L)
            .userType(UserType.CUSTOMER)
            .build();

    private static final WalletModification WALLET_MODIFICATION = WalletModification
            .builder()
            .name("wallet")
            .build();

    private static final WalletEntity WALLET_ENTITY = WalletEntity
            .builder()
            .name("wallet")
            .balance(new BigDecimal(0))
            .userId(1L)
            .userType(UserType.CUSTOMER)
            .build();

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
    void should_returnWalletByUserId() {
        //given
        var userId = 1L;
        var userType = UserType.MERCHANT;

        given(walletService.getWalletByUser(userId, userType)).willReturn(WALLET_ENTITY);

        //when
        var result = walletController.getWalletByUser(userId, userType);

        //then
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
}