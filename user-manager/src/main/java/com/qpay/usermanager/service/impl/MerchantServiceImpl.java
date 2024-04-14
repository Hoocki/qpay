package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.client.AuthenticationClient;
import com.qpay.usermanager.client.WalletClient;
import com.qpay.usermanager.mapper.MerchantMapper;
import com.qpay.usermanager.mapper.UserCredentialsMapper;
import com.qpay.usermanager.mapper.WalletMapper;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.dto.merchant.MerchantModification;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.MerchantService;
import com.qpay.usermanager.service.exception.NoMerchantFoundException;
import com.qpay.usermanager.service.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(isolation = Isolation.READ_COMMITTED)
public class MerchantServiceImpl implements MerchantService {

    private static final String GET_MERCHANT_BY_ID_ERROR = "Merchant with given id doesn't exist";

    private final MerchantRepository merchantRepository;

    private final MerchantMapper merchantMapper;

    private final UserCredentialsMapper userCredentialsMapper;

    private final WalletMapper walletMapper;

    private final CustomerRepository customerRepository;

    private final AuthenticationClient authenticationClient;

    private final WalletClient walletClient;

    @Override
    public List<MerchantEntity> getMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public MerchantEntity getMerchantById(final long id) {
        return merchantRepository.findById(id).orElseThrow(() -> new NoMerchantFoundException(GET_MERCHANT_BY_ID_ERROR));
    }

    @Override
    public MerchantEntity addMerchant(final MerchantCreation merchantCreation) {
        existsCustomerByEmail(merchantCreation.email());
        final var merchantEntity = merchantMapper.map(merchantCreation);
        merchantRepository.save(merchantEntity);
        final var newUserCredentials  = userCredentialsMapper.mapMerchantCreation(merchantCreation);
        authenticationClient.createUserCredentials(newUserCredentials );
        final var walletCreation = walletMapper.map(merchantEntity);
        walletClient.createWallet(walletCreation);
        return merchantEntity;
    }

    @Override
    public void deleteMerchant(final long id) {
        final var merchantEntity = getMerchantById(id);
        merchantRepository.deleteById(id);
        authenticationClient.deleteUserCredentials(merchantEntity.getEmail());
    }

    @Override
    public MerchantEntity updateMerchant(final long id, final MerchantModification merchantModification) {
        existsCustomerByEmail(merchantModification.email());
        final var merchantEntity = getMerchantById(id);
        final var previousEmail = merchantEntity.getEmail();
        merchantEntity.setName(merchantModification.name());
        merchantEntity.setEmail(merchantModification.email());
        merchantRepository.save(merchantEntity);
        final var userCredentialsModification = userCredentialsMapper.mapMerchantModification(merchantModification);
        authenticationClient.updateUserCredentials(previousEmail, userCredentialsModification);
        return merchantEntity;
    }

    private void existsCustomerByEmail(final String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Customer with this email already exists");
        }
    }
}
