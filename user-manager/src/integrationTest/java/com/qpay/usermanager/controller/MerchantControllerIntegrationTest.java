package com.qpay.usermanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.service.exception.CustomerAlreadyExistsException;
import com.qpay.usermanager.service.exception.NoMerchantFoundException;
import com.qpay.usermanager.service.impl.MerchantServiceImpl;
import com.qpay.usermanager.utility.PathsUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@WebMvcTest(controllers = MerchantController.class)
class MerchantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MerchantServiceImpl merchantService;

    private static final MerchantEntity MERCHANT_ENTITY = MerchantEntity.builder()
            .name("bob")
            .email("bob@gmail.com")
            .password("1234")
            .build();

    private static final MerchantCreation MERCHANT_CREATION = MerchantCreation.builder()
            .name("bob")
            .email("bob@gmail.com")
            .password("word")
            .build();

    @Test
    void should_returnMerchant() throws Exception {
        //given
        given(merchantService.getMerchantById(1L)).willReturn(MERCHANT_ENTITY);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get(PathsUtils.MERCHANTS_PATH + "/{id}", 1L))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(MERCHANT_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnNotFound_when_merchantIdNotFound() throws Exception {
        //given
        given(merchantService.getMerchantById(1L)).willThrow(new NoMerchantFoundException("not found"));

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.get(PathsUtils.MERCHANTS_PATH + "/{id}", 1L))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_returnMerchant_when_merchantAdded() throws Exception {
        //given
        given(merchantService.addMerchant(MERCHANT_CREATION)).willReturn(MERCHANT_ENTITY);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathsUtils.MERCHANTS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MERCHANT_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(MERCHANT_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnBadRequest_when_addMerchantInputInvalid() throws Exception {
        //given
        var invalidMerchantCreation = MerchantCreation.builder()
                .name("")
                .email("")
                .password("")
                .build();

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathsUtils.MERCHANTS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidMerchantCreation)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void should_returnException_when_newMerchantEmailAlreadyExistsInCustomers() throws Exception {
        // given
        var merchantCreation = MerchantCreation
                .builder()
                .name("s")
                .email("s")
                .password("s")
                .build();

        given(merchantService.addMerchant(merchantCreation)).willThrow(CustomerAlreadyExistsException.class);

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathsUtils.MERCHANTS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(merchantCreation)))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void should_returnMerchant_when_merchantUpdated() throws Exception {
        //given
        given(merchantService.updateMerchant(1L, MERCHANT_CREATION)).willReturn(MERCHANT_ENTITY);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathsUtils.MERCHANTS_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MERCHANT_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(MERCHANT_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_updatedMerchantEmailAlreadyExistsInCustomers() throws Exception {
        // given
        var merchantCreation = MerchantCreation
                .builder()
                .name("s")
                .email("s")
                .password("s")
                .build();

        given(merchantService.updateMerchant(1L, merchantCreation)).willThrow(CustomerAlreadyExistsException.class);

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathsUtils.MERCHANTS_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(merchantCreation)))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void should_returnBadRequest_when_updateMerchantInputInvalid() throws Exception {
        //given
        var invalidMerchantCreation = MerchantCreation.builder()
                .name("")
                .email("")
                .password("")
                .build();

        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathsUtils.MERCHANTS_PATH + "/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidMerchantCreation)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void should_deleteCustomer() throws Exception {
        //when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.delete(PathsUtils.MERCHANTS_PATH + "/{id}", 1L))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }
}