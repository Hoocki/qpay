package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.mapper.CustomerMapper;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.CustomerService;
import com.qpay.usermanager.service.exception.CustomerAlreadyExistsException;
import com.qpay.usermanager.service.exception.CustomerNotFoundException;
import com.qpay.usermanager.service.exception.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final MerchantRepository merchantRepository;

    public CustomerEntity getCustomerById(final long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public CustomerEntity addCustomer(final CustomerModification customerModification) {
        existsMerchantByEmail(customerModification.email());

        var customerEntity = customerMapper.map(customerModification);
        try {
            customerEntity = customerRepository.save(customerEntity);
        } catch (final DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new CustomerAlreadyExistsException(e.getMessage());
        }
        return customerEntity;
    }

    public CustomerEntity updateCustomer(final CustomerModification customerModification, final long id) {
        existsMerchantByEmail(customerModification.email());

        final var customerEntity = getCustomerById(id);
        customerEntity.setEmail(customerModification.email());
        customerEntity.setName(customerModification.name());
        customerEntity.setPassword(customerModification.password());
        return customerRepository.save(customerEntity);
    }

    public void deleteCustomer(final long id) {
        customerRepository.deleteById(id);
    }

    private void existsMerchantByEmail(final String email) {
        if (merchantRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
    }
}
