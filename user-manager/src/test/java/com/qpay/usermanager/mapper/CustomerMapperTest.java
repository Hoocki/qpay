package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void should_returnCustomerEntity_when_givenCustomerCreation() {
        // given
        var customerCreation = CustomerCreation.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        //when
        var result = customerMapper.map(customerCreation);

        //then
        var expectedCustomerEntity = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .build();

        assertThat(result).isEqualTo(expectedCustomerEntity);
    }
}