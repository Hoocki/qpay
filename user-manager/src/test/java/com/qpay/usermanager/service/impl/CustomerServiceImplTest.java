package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.mapper.CustomerMapper;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.exception.CustomerAlreadyExistsException;
import com.qpay.usermanager.service.exception.CustomerNotFoundException;
import com.qpay.usermanager.service.exception.EmailAlreadyExistsException;
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
    private MerchantRepository merchantRepository;

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
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        given(customerMapper.map(customerModification)).willReturn(expectedCustomer);
        given(customerRepository.save(expectedCustomer)).willReturn(expectedCustomer);

        // when
        var result = customerService.addCustomer(customerModification);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
    }

    @Test
    void should_throwEmailAlreadyExistsException_when_addCustomerThatAlreadyExistsWithGivenEmailInMerchants() {
        // given
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("admin@gmail.com")
                .password("qwerty")
                .build();

        given(merchantRepository.existsByEmail("admin@gmail.com")).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> customerService.addCustomer(customerModification));

        //then
        assertThat(throwable).isInstanceOf(EmailAlreadyExistsException.class);;
    }

    @Test
    void should_updateCustomer() {
        // given
        var id = 1L;
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .build();

        var customer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Andrey")
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));
        given(merchantRepository.existsByEmail(customerModification.email())).willReturn(false);
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
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // when
        var thrown = catchThrowable(() -> customerService.updateCustomer(customerModification, id));

        // then
        assertThat(thrown)
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void should_throwEmailAlreadyExistsException_when_updateCustomerThatAlreadyExistsWithGivenEmailInMerchants() {
        // given
        var customerModification = CustomerModification.builder()
                .name("Andrey")
                .email("admin@gmail.com")
                .password("qwerty")
                .build();

        given(merchantRepository.existsByEmail("admin@gmail.com")).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> customerService.updateCustomer(customerModification, 1L));

        //then
        assertThat(throwable).isInstanceOf(EmailAlreadyExistsException.class);;
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