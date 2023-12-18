package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity map(final CustomerModification customerModification) {
        return CustomerEntity.builder()
                .name(customerModification.name())
                .email(customerModification.email())
                .password(customerModification.password())
                .walletId(customerModification.walletId())
                .build();
    }
}
