package com.qpay.paymentmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.exception.NoWalletFoundException;
import com.qpay.paymentmanager.service.impl.WalletServiceImpl;
import com.qpay.paymentmanager.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = WalletController.class)
class WalletControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WalletServiceImpl walletService;

    private static final WalletCreation WALLET_CREATION = WalletCreation.builder().name("wallet").userId(1L).userType(UserType.CUSTOMER).build();

    private static final WalletModification WALLET_MODIFICATION = WalletModification.builder().name("wallet").build();

    private static final WalletTopUp WALLET_TOP_UP = WalletTopUp.builder().amount(BigDecimal.valueOf(100)).build();

    private static final WalletPayment WALLET_PAYMENT = WalletPayment.builder().walletIdFrom(1L).walletIdTo(2L).amount(BigDecimal.valueOf(100)).build();

    private static final WalletEntity WALLET_ENTITY = WalletEntity.builder().name("wallet").balance(new BigDecimal(0)).userId(1L).userType(UserType.CUSTOMER).build();

    @Test
    void should_returnWallet() throws Exception {
        // given
        var id = 1L;
        given(walletService.getWalletById(id)).willReturn(WALLET_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get(PathUtils.WALLET_PATH + "/{id}", id))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = objectMapper.writeValueAsString(WALLET_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_walletDoesNotExist() throws Exception {
        // given
        var id = 1L;
        given(walletService.getWalletById(id)).willThrow(new NoWalletFoundException());

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.get(PathUtils.WALLET_PATH + "/{id}", id))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_returnWallet_when_walletAdded() throws Exception {
        // given
        given(walletService.addWallet(WALLET_CREATION)).willReturn(WALLET_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.WALLET_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(WALLET_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = objectMapper.writeValueAsString(WALLET_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_newWalletHasInvalidInput() throws Exception {
        // given
        var invalidWallet = WalletModification
                .builder()
                .build();

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.WALLET_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidWallet)))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void should_returnWallet_when_walletUpdated() throws Exception {
        // given
        var id = 1L;

        given(walletService.updateWallet(WALLET_MODIFICATION, id)).willReturn(WALLET_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathUtils.WALLET_PATH + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(WALLET_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(WALLET_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_updatedWalletHasInvalidInput() throws Exception {
        // given
        var invalidWallet = WalletModification
                .builder()
                .build();

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathUtils.WALLET_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidWallet)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void should_deleteWallet() throws Exception {
        // given
        var id = 1L;

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.delete(PathUtils.WALLET_PATH + "/{id}", id))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void should_returnUpdatedWallet_when_providePaymentBetweenWallets() throws Exception {
        // given
        var id = 1L;
        var walletBeforePayment = WalletEntity.builder()
                .name("wallet")
                .balance(new BigDecimal(100))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletService.paymentWallet(WALLET_PAYMENT)).willReturn(WALLET_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.WALLET_PATH + "/p2b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(WALLET_PAYMENT)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = objectMapper.writeValueAsString(WALLET_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnUpdatedWallet_when_walletTopUp() throws Exception {
        // given
        var id = 1L;
        var expectedWallet = WalletEntity.builder()
                .name("wallet")
                .balance(new BigDecimal(100))
                .userId(1L)
                .userType(UserType.CUSTOMER)
                .build();

        given(walletService.topUpWallet(WALLET_TOP_UP, id)).willReturn(expectedWallet);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.WALLET_PATH + "/topUp/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(WALLET_TOP_UP)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = objectMapper.writeValueAsString(expectedWallet);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

}