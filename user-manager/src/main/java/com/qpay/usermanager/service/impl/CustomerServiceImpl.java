package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.client.AuthenticationClient;
import com.qpay.usermanager.client.WalletClient;
import com.qpay.usermanager.mapper.CustomerMapper;
import com.qpay.usermanager.mapper.UserCredentialsMapper;
import com.qpay.usermanager.mapper.WalletMapper;
import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.CustomerService;
import com.qpay.usermanager.service.exception.CustomerNotFoundException;
import com.qpay.usermanager.service.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    private final WalletMapper walletMapper;

    private final UserCredentialsMapper userCredentialsMapper;

    private final MerchantRepository merchantRepository;

    private final AuthenticationClient authenticationClient;

    private final WalletClient walletClient;

    public CustomerEntity getCustomerById(final long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public CustomerEntity addCustomer(final CustomerCreation customerCreation) {
        existsMerchantByEmail(customerCreation.email());
        var customerEntity = customerMapper.map(customerCreation);
        try {
            customerEntity = customerRepository.save(customerEntity);
        } catch (final DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new UserAlreadyExistsException(e.getMessage());
        }
        final var createUserCredentials  = userCredentialsMapper.mapCustomerCreation(customerCreation);
        authenticationClient.createUserCredentials(createUserCredentials);
        final var walletCreation = walletMapper.map(customerEntity);
        walletClient.createWallet(walletCreation);
        return customerEntity;
    }

    public CustomerEntity updateCustomer(final CustomerModification customerModification, final long id) {
        existsMerchantByEmail(customerModification.email());
        final var customerEntity = getCustomerById(id);
        final var previousEmail = customerEntity.getEmail();
        customerEntity.setEmail(customerModification.email());
        customerEntity.setName(customerModification.name());
        final var savedCustomerEntity = customerRepository.save(customerEntity);
        final var userCredentialsModification = userCredentialsMapper.mapCustomerModification(customerModification);
        authenticationClient.updateUserCredentials(previousEmail, userCredentialsModification);
        return savedCustomerEntity;
    }

    public void deleteCustomer(final long id) {
        final var customerEntity = getCustomerById(id);
        customerRepository.deleteById(id);
        authenticationClient.deleteUserCredentials(customerEntity.getEmail());
    }

    private void existsMerchantByEmail(final String email) {
        if (merchantRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Merchant with this email already exists");
        }
    }
}