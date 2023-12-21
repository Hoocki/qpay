package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.mapper.CustomerMapper;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.service.exception.CustomerAlreadyExistsException;
import com.qpay.usermanager.service.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Test
    void should_returnCustomer_when_idMatched() {
        // given
        var id = 1L;
        var customer1 = CustomerEntity.builder()
                .id(id)
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .walletId(1L)
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.of(customer1));

        // when
        var result = customerService.getCustomerById(id);

        // then
        assertThat(result).isEqualTo(customer1);
    }

    @Test
    void should_throwCustomerNotFoundException_when_customerWithThisId_doesntExist() {
        // given
        var id = 999L;
        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // when
        var thrown = catchThrowable(() -> customerService.getCustomerById(id));

        // then
        assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void should_addCustomer() {
        // given
        var email = "admin@gmail.com";
        var customerModification = CustomerModification.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .walletId(1L)
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .walletId(1L)
                .build();

        given(customerMapper.map(customerModification)).willReturn(expectedCustomer);
        given(customerRepository.save(expectedCustomer)).willReturn(expectedCustomer);

        // when
        var result = customerService.addCustomer(customerModification);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
    }

    @Test
    void should_throwCustomerAlreadyExistsException_when_addCustomerThatAlreadyExistsWithGivenEmail() {
        // given
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("admin@gmail.com")
                .password("qwerty")
                .walletId(2L)
                .build();

        var customer1 = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .walletId(1L)
                .build();

        var customer2 = CustomerEntity.builder()
                .name("Andrey")
                .email("admin@gmail.com")
                .password("qwerty")
                .walletId(2L)
                .build();

        given(customerMapper.map(customerModification)).willReturn(customer2);
        given(customerRepository.save(customer2))
                .willThrow(DataIntegrityViolationException.class);

        // when
        var thrown = catchThrowable(() -> customerService.addCustomer(customerModification));

        // then
        assertThat(thrown)
                .isInstanceOf(CustomerAlreadyExistsException.class);
    }

    @Test
    void should_updateCustomer() {
        // given
        var id = 1L;
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .walletId(1L)
                .build();

        var customer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .walletId(1L)
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Andrey")
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .walletId(1L)
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));
        given(customerRepository.existsByEmail(customerModification.email())).willReturn(false);
        given(customerRepository.save(expectedCustomer)).willReturn(expectedCustomer);

        // when
        var result = customerService.updateCustomer(customerModification, id);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
    }

    @Test
    void should_throwCustomerNotFoundException_when_updateCustomerDoesNotExistWithGivenId() {
        // given
        var id = 999L;
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .walletId(1L)
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // when
        var thrown = catchThrowable(() -> customerService.updateCustomer(customerModification, id));

        // then
        assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void should_throwCustomerAlreadyExistsException_when_customerModificationContainsEmailThatAlreadyExists() {
        // given
        var id = 1L;
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("qwerty@gmail.com")
                .password("qwerty")
                .walletId(1L)
                .build();

        var customer1 = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .walletId(1L)
                .build();

        var customer2 = CustomerEntity.builder()
                .name("Anton")
                .email("qwerty@gmail.com")
                .password("password1234")
                .walletId(2L)
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.of(customer1));
        given(customerRepository.existsByEmail(customerModification.email())).willReturn(true);

        // when
        var thrown = catchThrowable(() -> customerService.updateCustomer(customerModification, id));

        // then
        assertThat(thrown)
                .isInstanceOf(CustomerAlreadyExistsException.class);
    }

    @Test
    void should_deleteCustomer() {
        // given
        var id = 1L;

        // when
        customerService.deleteCustomer(id);

        // then
        then(customerRepository).should().deleteById(id);
    }

}