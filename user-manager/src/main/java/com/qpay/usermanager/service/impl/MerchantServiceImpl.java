package com.qpay.usermanager.service.impl;

import com.qpay.usermanager.mapper.MerchantMapper;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.repository.CustomerRepository;
import com.qpay.usermanager.repository.MerchantRepository;
import com.qpay.usermanager.service.MerchantService;
import com.qpay.usermanager.service.exception.CustomerAlreadyExistsException;
import com.qpay.usermanager.service.exception.NoMerchantFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private static final String GET_MERCHANT_BY_ID_ERROR = "Merchant with given id doesn't exist";

    private final MerchantRepository merchantRepository;

    private final MerchantMapper merchantMapper;

    private final CustomerRepository customerRepository;

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

        final MerchantEntity merchantEntity = merchantMapper.map(merchantCreation);
        merchantRepository.save(merchantEntity);
        return merchantEntity;
    }

    @Override
    public void deleteMerchant(final long id) {
        merchantRepository.deleteById(id);
    }

    @Override
    public MerchantEntity updateMerchant(final long id, final MerchantCreation merchantCreation) {
        existsCustomerByEmail(merchantCreation.email());

        final MerchantEntity merchantEntity = getMerchantById(id);
        merchantEntity.setName(merchantCreation.name());
        merchantEntity.setEmail(merchantCreation.email());
        merchantEntity.setPassword(merchantCreation.password());
        merchantRepository.save(merchantEntity);
        return merchantEntity;
    }

    private void existsCustomerByEmail(final String email) {
        if (customerRepository.existsByEmail(email)) {
            throw new CustomerAlreadyExistsException();
        }
    }
}
