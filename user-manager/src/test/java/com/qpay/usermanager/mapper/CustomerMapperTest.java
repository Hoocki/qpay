package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void should_returnCustomerEntity_when_givenCustomerModification() {
        // given
        var customerModification = CustomerModification.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        //when
        var result = customerMapper.map(customerModification);

        //then
        var expectedCustomerEntity = CustomerEntity.builder()
                .name("Roman")
                .email("admin@gmail.com")
                .password("password")
                .build();

        assertThat(result).isEqualTo(expectedCustomerEntity);
    }
}