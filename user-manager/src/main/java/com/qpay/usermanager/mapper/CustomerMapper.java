package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.libs.models.CustomerReportInfo;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity map(final CustomerCreation customerCreation) {
        return CustomerEntity.builder()
                .name(customerCreation.name())
                .email(customerCreation.email())
                .build();
    }

    public CustomerReportInfo map(final CustomerEntity customer) {
        return CustomerReportInfo.builder()
                .email(customer.getEmail())
                .name(customer.getName())
                .build();
    }
}
