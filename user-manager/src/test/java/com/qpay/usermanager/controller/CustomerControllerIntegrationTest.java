package com.qpay.usermanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.service.exception.CustomerNotFoundException;
import com.qpay.usermanager.service.impl.CustomerServiceImpl;
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


@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerServiceImpl customerService;

    private final CustomerEntity CUSTOMER_ENTITY = CustomerEntity
            .builder()
            .name("Roman")
            .email("admin@gmail.com")
            .password("password")
            .build();

    private final CustomerModification CUSTOMER_MODIFICATION = CustomerModification
            .builder()
            .name("Roman")
            .email("admin@gmail.com")
            .password("password")
            .build();

    @Test
    void should_returnCustomer() throws Exception {
        // given
        var id = 1L;
        given(customerService.getCustomerById(id)).willReturn(CUSTOMER_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get(PathsUtils.CUSTOMER_PATH + "/{id}", id))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = objectMapper.writeValueAsString(CUSTOMER_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_customerDoesNotExist() throws Exception {
        // given
        var id = 999L;
        given(customerService.getCustomerById(id)).willThrow(new CustomerNotFoundException());

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.get(PathsUtils.CUSTOMER_PATH + "/{id}", id))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void should_returnCustomer_when_customerAdded() throws Exception {
        // given
        given(customerService.addCustomer(CUSTOMER_MODIFICATION)).willReturn(CUSTOMER_ENTITY);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathsUtils.CUSTOMER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CUSTOMER_MODIFICATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        var expectedResponseBody = objectMapper.writeValueAsString(CUSTOMER_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_addCustomerHasInvalidInput() throws Exception {
        // given
        var invalidCustomerModification = CustomerModification
                .builder()
                .name("")
                .email("")
                .password("")
                .build();

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathsUtils.CUSTOMER_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCustomerModification)))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void should_returnCustomer_when_customerUpdated() throws Exception {
        // given
        var id = 1L;
        var customerModificationAnother = CustomerModification.builder()
                .name("Anatoly")
                .email("anatoly@gmail.com")
                .password("password1234")
                .build();
        var expectedCustomer = CustomerEntity.builder()
                .name("Anatoly")
                .email("anatoly@gmail.com")
                .password("password1234")
                .build();

        given(customerService.updateCustomer(customerModificationAnother, id)).willReturn(expectedCustomer);

        // when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathsUtils.CUSTOMER_PATH + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerModificationAnother)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(expectedCustomer);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnException_when_updateCustomerHasInvalidInput() throws Exception {
        // given
        var id = 1L;
        var customerModificationAnother = CustomerModification.builder()
                .name("")
                .email("")
                .password("")
                .build();

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put(PathsUtils.CUSTOMER_PATH + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerModificationAnother)))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void should_deleteCustomer() throws Exception {
        // given
        var id = 1L;

        // when
        var status = mockMvc
                .perform(MockMvcRequestBuilders.delete(PathsUtils.CUSTOMER_PATH + "/{id}", id))
                .andReturn()
                .getResponse()
                .getStatus();

        // then
        assertThat(status).isEqualTo(HttpStatus.OK.value());
    }
}