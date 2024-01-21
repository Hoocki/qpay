package com.qpay.paymentmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.PaymentService;
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

@WebMvcTest(controllers = PaymentController.class)
class PaymentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    private static final WalletTopUp WALLET_TOP_UP = WalletTopUp
            .builder()
            .amount(BigDecimal.valueOf(100))
            .build();

    private static final WalletPayment WALLET_PAYMENT = WalletPayment
            .builder()
            .emailFrom("example1@gmail.com")
            .walletIdFrom(1L)
            .emailTo("example2@gmail.com")
            .walletIdTo(2L)
            .amount(BigDecimal.valueOf(100))
            .build();

    private static final WalletEntity WALLET_ENTITY = WalletEntity
            .builder()
            .name("wallet")
            .balance(new BigDecimal(0)).userId(1L)
            .userType(UserType.CUSTOMER)
            .build();

    @Test
    void should_returnUpdatedWallet_when_makePaymentBetweenWallets() throws Exception {
        // given
        given(paymentService.makePayment(WALLET_PAYMENT)).willReturn(WALLET_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.PAYMENT_PATH + "/p2b")
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
    void should_throwException_when_walletPaymentHasInvalidInput() throws Exception {
        // given
        final var invalidWalletPayment = WalletPayment
                .builder()
                .emailFrom("example@gmail.com")
                .walletIdFrom(1L)
                .emailTo("")
                .walletIdTo(2L)
                .amount(BigDecimal.valueOf(100))
                .build();

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.PAYMENT_PATH + "/p2b")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidWalletPayment)))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
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

        given(paymentService.topUp(WALLET_TOP_UP, id)).willReturn(expectedWallet);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.PAYMENT_PATH + "/topUp/{id}", id)
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