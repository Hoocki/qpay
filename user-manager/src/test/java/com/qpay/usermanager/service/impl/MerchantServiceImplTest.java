package com.qpay.usermanager.service.impl;

import com.qpay.libs.models.UserType;
import com.qpay.usermanager.client.AuthenticationClient;
import com.qpay.usermanager.client.WalletClient;
import com.qpay.usermanager.mapper.UserCredentialsMapper;
import com.qpay.usermanager.mapper.MerchantMapper;
import com.qpay.usermanager.mapper.WalletMapper;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsCreation;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsModification;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.dto.merchant.MerchantModification;
import com.qpay.usermanager.model.dto.wallet.WalletCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.exception.NoMerchantFoundException;
import com.qpay.usermanager.service.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MerchantServiceImplTest {
    @InjectMocks
    private MerchantServiceImpl merchantService;

    @Mock
    private MerchantRepository merchantRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MerchantMapper merchantMapper;

    @Mock
    private WalletMapper walletMapper;

    @Mock
    private UserCredentialsMapper userCredentialsMapper;

    @Mock
    private AuthenticationClient authenticationClient;

    @Mock
    private WalletClient walletClient;

    private static final String EMAIL = "bob@gmail.com";

    private static final MerchantCreation MERCHANT_CREATION = MerchantCreation.builder()
            .name("bob")
            .email("bob@gmail.com")
            .password("word")
            .build();

    private static final MerchantModification MERCHANT_MODIFICATION = MerchantModification.builder()
            .name("bob")
            .email("bob@gmail.com")
            .password("word")
            .build();

    @Test
    void should_returnMerchants() {
        //given
        var merchant1 = MerchantEntity.builder()
                .name("bob")
                .email("cool_bob@gmail.com")
                .build();

        var merchant2 = MerchantEntity.builder()
                .name("greg")
                .email("geg@gmail.com")
                .build();

        List<MerchantEntity> list = List.of(merchant1, merchant2);
        given(merchantRepository.findAll()).willReturn(list);

        //when
        var result = merchantService.getMerchants();

        //then
        assertThat(result).isEqualTo(list);
    }

    @Test
    void should_returnMerchantById() {
        //given
        var merchant1 = MerchantEntity.builder()
                .name("bob")
                .email("cool_bob@gmail.com")
                .build();

        given(merchantRepository.findById(1L)).willReturn(Optional.of(merchant1));

        //when
        var result = merchantService.getMerchantById(1);

        //then
        assertThat(result).isEqualTo(merchant1);
    }

    @Test
    void should_throwNoMerchantFoundException_when_merchantByIdNotFound() {
        //when
        Throwable throwable = catchThrowable(() -> merchantService.getMerchantById(1));

        //then
        assertThat(throwable).isInstanceOf(NoMerchantFoundException.class);
    }

    @Test
    void should_addMerchant() {
        //given
        var merchantId = 1L;
        var expectedMerchant = MerchantEntity.builder()
                .id(1L)
                .name("bob")
                .email(EMAIL)
                .build();

        var createdUser = UserCredentialsCreation.builder()
                .email(EMAIL)
                .password("word")
                .userType(UserType.MERCHANT)
                .userId(merchantId)
                .build();

        var walletCreation = WalletCreation.builder()
                .name("wallet")
                .userId(1L)
                .userType(UserType.MERCHANT)
                .build();

        given(merchantMapper.map(MERCHANT_CREATION)).willReturn(expectedMerchant);
        given(userCredentialsMapper.mapMerchantCreation(MERCHANT_CREATION, merchantId)).willReturn(createdUser);
        given(walletMapper.map(expectedMerchant)).willReturn(walletCreation);

        //when
        var result = merchantService.addMerchant(MERCHANT_CREATION);

        //then
        assertThat(result).isEqualTo(expectedMerchant);
        then(authenticationClient).should().createUserCredentials(createdUser);
        then(walletClient).should().createWallet(walletCreation);
    }

    @Test
    void should_throwCustomerAlreadyExistsException_when_newMerchantEmailAlreadyExistsInCustomers() {
        //given
        given(customerRepository.existsByEmail("bob@gmail.com")).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> merchantService.addMerchant(MERCHANT_CREATION));

        //then
        assertThat(throwable).isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    void should_deleteMerchant() {
        //given
        var id = 1L;
        var merchant = MerchantEntity.builder()
                .name("bob")
                .email(EMAIL)
                .build();

        given(merchantRepository.findById(id)).willReturn(Optional.of(merchant));

        //when
        merchantService.deleteMerchant(id);

        //then
        then(merchantRepository).should().deleteById(id);
        then(authenticationClient).should().deleteUserCredentials(EMAIL);
    }

    @Test
    void should_updateMerchant() {
        //given
        var previousEmail = "greg@gmail.com";
        var merchantModification = MerchantModification.builder()
                .name("bob")
                .email(EMAIL)
                .password("word")
                .build();

        var merchant = MerchantEntity.builder()
                .name("greg")
                .email("greg@gmail.com")
                .build();

        var updatedUser = UserCredentialsModification.builder()
                .email(EMAIL)
                .password("word")
                .build();

        var expectedMerchant = MerchantEntity.builder()
                .name("bob")
                .email(EMAIL)
                .build();

        given(merchantRepository.findById(1L)).willReturn(Optional.of(merchant));
        given(merchantRepository.save(expectedMerchant)).willReturn(expectedMerchant);
        given(userCredentialsMapper.mapMerchantModification(merchantModification)).willReturn(updatedUser);

        //when
        var result = merchantService.updateMerchant(1L, MERCHANT_MODIFICATION);

        //then
        assertThat(result).isEqualTo(expectedMerchant);
        then(authenticationClient).should().updateUserCredentials(previousEmail, updatedUser);
    }

    @Test
    void should_throwCustomerAlreadyExistsException_when_updatedMerchantEmailAlreadyExistsInCustomers() {
        //given
        given(customerRepository.existsByEmail("bob@gmail.com")).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> merchantService.updateMerchant(1L, MERCHANT_MODIFICATION));

        //then
        assertThat(throwable).isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    void should_throwNoMerchantFoundException_when_updateMerchantIdNotFound() {
        //when
        Throwable throwable = catchThrowable(() -> merchantService.updateMerchant(1L, MERCHANT_MODIFICATION));

        //then
        assertThat(throwable).isInstanceOf(NoMerchantFoundException.class);
    }
}