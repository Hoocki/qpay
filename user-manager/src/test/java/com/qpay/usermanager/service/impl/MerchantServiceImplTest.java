package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.mapper.MerchantMapper;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.exception.NoMerchantFoundException;
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
    private MerchantMapper merchantMapper;

    private static final String EMAIL = "bob@gmail.com";

    private static final MerchantCreation MERCHANT_CREATION = MerchantCreation.builder().name("bob").email("bob@gmail.com").password("word").build();

    @Test
    void should_returnMerchants() {
        //given
        var merchant1 = MerchantEntity.builder()
                .name("bob")
                .email("cool_bob@gmail.com")
                .password("1234")
                .build();

        var merchant2 = MerchantEntity.builder()
                .name("greg")
                .email("geg@gmail.com")
                .password("5678")
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
                .password("1234")
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
       var expectedMerchant = MerchantEntity.builder()
               .name("bob")
               .email(EMAIL)
               .password("word")
               .build();

       given(merchantMapper.map(MERCHANT_CREATION)).willReturn(expectedMerchant);

       //when
       var result = merchantService.addMerchant(MERCHANT_CREATION);

       //then
        assertThat(result).isEqualTo(expectedMerchant);
    }

    @Test
    void should_deleteMerchant() {
        //when
        merchantService.deleteMerchant(1L);

        //then
        then(merchantRepository).should().deleteById(1L);
    }

    @Test
    void should_updateMerchant() {
        //given
        var merchant = MerchantEntity.builder()
                .name("greg")
                .email("greg@gmail.com")
                .password("pass")
                .build();
        var expectedMerchant = MerchantEntity.builder()
                .name("bob")
                .email(EMAIL)
                .password("word")
                .build();

        given(merchantRepository.findById(1L)).willReturn(Optional.of(merchant));

        //when
        var result = merchantService.updateMerchant(1L, MERCHANT_CREATION);

        //then
        assertThat(result).isEqualTo(expectedMerchant);
    }

    @Test
    void should_throwNoMerchantFoundException_when_updateMerchantIdNotFound() {
        //when
        Throwable throwable = catchThrowable(() -> merchantService.updateMerchant(1L, MERCHANT_CREATION));

        //then
        assertThat(throwable).isInstanceOf(NoMerchantFoundException.class);
    }
}