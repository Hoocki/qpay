package com.qpay.usermanager.controller;

import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Test
    void should_returnCustomer() {
        // given
        var id = 1L;
        var customer1 = CustomerEntity.builder()
                .id(id)
                .name("Roman")
                .email("admin@gmail.com")
                .build();

        given(customerService.getCustomerById(id)).willReturn(customer1);

        // when
        var result = customerController.getCustomer(id);

        // then
        assertThat(result).isEqualTo(customer1);
    }

    @Test
    void should_addCustomer() {
        // given
        var customerCreation = CustomerCreation.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .build();

        given(customerService.addCustomer(customerCreation)).willReturn(expectedCustomer);

        // when
        var result = customerController.addCustomer(customerCreation);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
    }

    @Test
    void should_updateCustomer() {
        // given
        var id = 1L;
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("qwerty@gmail.com")
                .password("password1234")
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Andrey")
                .email("qwerty@gmail.com")
                .build();

        given(customerService.updateCustomer(customerModification, id)).willReturn(expectedCustomer);

        // when
        var result = customerController.updateCustomer(customerModification, id);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
    }

    @Test
    void should_deleteCustomer() {
        // given
        var id = 1L;

        // when
        customerController.deleteCustomer(id);

        // then
        then(customerService).should().deleteCustomer(id);
    }
}