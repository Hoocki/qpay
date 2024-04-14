package com.qpay.usermanager.service;

import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;

public interface CustomerService {

    CustomerEntity getCustomerById(long id);

    CustomerEntity addCustomer(CustomerCreation customerCreation);

    CustomerEntity updateCustomer(CustomerModification customerModification, long id);

    void deleteCustomer(long id);
}
