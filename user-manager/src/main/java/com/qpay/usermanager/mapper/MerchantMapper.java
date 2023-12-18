package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {
    public MerchantEntity map(final MerchantCreation merchantCreation) {
        return MerchantEntity
                .builder()
                .name(merchantCreation.getName())
                .email(merchantCreation.getEmail())
                .password(merchantCreation.getPassword())
                .build();
    }
}
