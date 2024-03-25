package com.qpay.usermanager.controller;

import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.dto.merchant.MerchantModification;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.service.MerchantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class MerchantControllerTest {

    @InjectMocks
    private MerchantController merchantController;

    @Mock
    private MerchantService merchantService;

    private static final MerchantEntity MERCHANT_ENTITY = MerchantEntity.builder()
            .name("bob")
            .email("bob@gmail.com")
            .build();

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
    void should_returnMerchant() {
        //given
        given(merchantService.getMerchantById(1L)).willReturn(MERCHANT_ENTITY);

        //when
        var result = merchantController.getMerchantById(1L);

        //then
        assertThat(result).isEqualTo(MERCHANT_ENTITY);
    }

    @Test
    void should_addCustomer() {
        //given
        given(merchantService.addMerchant(MERCHANT_CREATION)).willReturn(MERCHANT_ENTITY);

        //when
        var result = merchantController.addMerchant(MERCHANT_CREATION);

        //then
        assertThat(result).isEqualTo(MERCHANT_ENTITY);
    }

    @Test
    void should_updateCustomer() {
        //given
        given(merchantService.updateMerchant(1L, MERCHANT_MODIFICATION)).willReturn(MERCHANT_ENTITY);

        //when
        var result = merchantController.updateMerchant(1L, MERCHANT_MODIFICATION);

        //then
        assertThat(result).isEqualTo(MERCHANT_ENTITY);
    }

    @Test
    void should_deleteMerchant() {
        //when
        merchantController.deleteMerchant(1L);

        //then
        then(merchantService).should().deleteMerchant(1L);
    }
}