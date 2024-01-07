package com.qpay.paymentmanager.service.impl;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.mapper.WalletMapper;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.repository.WalletRepository;
import com.qpay.paymentmanager.service.exception.NoWalletFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @InjectMocks
    private WalletServiceImpl walletService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private WalletMapper walletMapper;

    private static final WalletCreation WALLET_CREATION = WalletCreation.builder().name("wallet").userId(1L).userType(UserType.CUSTOMER).build();

    private static final WalletModification WALLET_MODIFICATION = WalletModification.builder().name("wallet").build();
    
    private static final WalletEntity WALLET_ENTITY = WalletEntity.builder().name("wallet").balance(new BigDecimal(0)).userId(1L).userType(UserType.CUSTOMER).build();

    @Test
    void should_getWalletById() {
        //when
        var id = 1L;

        given(walletRepository.findById(id)).willReturn(Optional.of(WALLET_ENTITY));

        // when
        var result = walletService.getWalletById(id);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_throwNoWalletFoundException_when_walletNotFound() {
        // given
        var id = 1L;
        given(walletRepository.findById(id)).willReturn(Optional.empty());

        // when
        var thrown = catchThrowable(() -> walletService.getWalletById(id));

        // then
        assertThat(thrown)
                .isInstanceOf(NoWalletFoundException.class);
    }

    @Test
    void should_addWallet() {
        // given
        given(walletMapper.map(WALLET_CREATION)).willReturn(WALLET_ENTITY);
        given(walletRepository.save(WALLET_ENTITY)).willReturn(WALLET_ENTITY);

        // when
        var result = walletService.addWallet(WALLET_CREATION);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_updateWallet() {
        // given
        var id = 1L;
        var wallet = WalletEntity.builder()
                .name("adasdasd")
                .balance(new BigDecimal(0))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletRepository.findById(id)).willReturn(Optional.of(wallet));
        given(walletRepository.save(WALLET_ENTITY)).willReturn(WALLET_ENTITY);

        // when
        var result = walletService.updateWallet(WALLET_MODIFICATION, id);

        // then
        assertThat(result).isEqualTo(WALLET_ENTITY);
    }

    @Test
    void should_deleteWallet() {
        //when
        walletService.deleteWallet(1L);

        //then
        then(walletRepository).should().deleteById(1L);
    }
}