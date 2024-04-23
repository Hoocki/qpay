package com.qpay.usermanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsCreation;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsModification;
import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.dto.merchant.MerchantModification;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialsMapperTest {

    private final UserCredentialsMapper userCredentialsMapper = new UserCredentialsMapper();

    @Test
    void should_returnUserCredentialsCreation_when_givenCustomerCreation() {
        // given
        var customerId = 1L;
        var customerCreation = CustomerCreation.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        // when
        var result = userCredentialsMapper.mapCustomerCreation(customerCreation, customerId);

        // then
        var expectedUser = UserCredentialsCreation.builder()
                .email("admin@gmail.com")
                .password("password")
                .userType(UserType.CUSTOMER)
                .userId(customerId)
                .build();

        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void should_returnUserCredentialsModification_when_givenCustomerModification() {
        // given
        var customerModification = CustomerModification.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        // when
        var result = userCredentialsMapper.mapCustomerModification(customerModification);

        // then
        var expectedUser = UserCredentialsModification.builder()
                .email("admin@gmail.com")
                .password("password")
                .build();

        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void should_returnUserCredentialsCreation_when_givenMerchantCreation() {
        // given
        var merchantId = 1L;
        var merchantCreation = MerchantCreation.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        // when
        var result = userCredentialsMapper.mapMerchantCreation(merchantCreation, merchantId);

        // then
        var expectedUser = UserCredentialsCreation.builder()
                .email("admin@gmail.com")
                .password("password")
                .userType(UserType.MERCHANT)
                .userId(merchantId)
                .build();

        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void should_returnUserCredentialsModification_when_givenMerchantModification() {
        // given
        var merchantModification = MerchantModification.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        // when
        var result = userCredentialsMapper.mapMerchantModification(merchantModification);

        // then
        var expectedUser = UserCredentialsModification.builder()
                .email("admin@gmail.com")
                .password("password")
                .build();

        assertThat(result).isEqualTo(expectedUser);
    }
}