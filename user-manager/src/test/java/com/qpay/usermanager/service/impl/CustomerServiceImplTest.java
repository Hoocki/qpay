package com.qpay.usermanager.service.impl;

import com.qpay.libs.models.UserType;
import com.qpay.usermanager.client.AuthenticationClient;
import com.qpay.usermanager.mapper.UserCredentialsMapper;
import com.qpay.usermanager.mapper.CustomerMapper;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsCreation;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsModification;
import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.exception.CustomerNotFoundException;
import com.qpay.usermanager.service.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Mock
    private UserCredentialsMapper userCredentialsMapper;

    @Mock
    private AuthenticationClient authenticationClient;

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
        var customerCreation = CustomerCreation.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        var createdUser = UserCredentialsCreation.builder()
                .email("admin@gmail.com")
                .password("password")
                .userType(UserType.CUSTOMER)
                .build();

        given(customerMapper.map(customerCreation)).willReturn(expectedCustomer);
        given(customerRepository.save(expectedCustomer)).willReturn(expectedCustomer);
        given(userCredentialsMapper.mapCustomerCreation(customerCreation)).willReturn(createdUser);

        // when
        var result = customerService.addCustomer(customerCreation);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
        then(authenticationClient).should().createUserCredentials(createdUser);
    }

    @Test
    void should_throwEmailAlreadyExistsException_when_addCustomerThatAlreadyExistsWithGivenEmailInMerchants() {
        // given
        var customerCreation = CustomerCreation.builder()
                .name("Andrey")
                .email("admin@gmail.com")
                .password("qwerty")
                .build();

        given(merchantRepository.existsByEmail("admin@gmail.com")).willReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> customerService.addCustomer(customerCreation));

        //then
        assertThat(throwable).isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    void should_updateCustomer() {
        // given
        var id = 1L;
        var previousEmail = "admin@gmail.com";
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

        var updatedUser = UserCredentialsModification.builder()
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .build();

        var expectedCustomer = CustomerEntity.builder()
                .name("Andrey")
                .email("newAdmin@gmail.com")
                .password("qwerty")
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));
        given(merchantRepository.existsByEmail(customerModification.email())).willReturn(false);
        given(customerRepository.save(expectedCustomer)).willReturn(expectedCustomer);
        given(userCredentialsMapper.mapCustomerModification(customerModification)).willReturn(updatedUser);

        // when
        var result = customerService.updateCustomer(customerModification, id);

        // then
        assertThat(result).isEqualTo(expectedCustomer);
        then(authenticationClient).should().updateUserCredentials(previousEmail, updatedUser);
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
        assertThat(throwable).isInstanceOf(UserAlreadyExistsException.class);
    }

    @Test
    void should_deleteCustomer() {
        // given
        var id = 1L;
        var email = "admin@gmail.com";
        var customer = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        given(customerRepository.findById(id)).willReturn(Optional.of(customer));

        // when
        customerService.deleteCustomer(id);

        // then
        then(customerRepository).should().deleteById(id);
        then(authenticationClient).should().deleteUserCredentials(email);
    }
}