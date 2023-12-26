package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {
    public MerchantEntity map(final MerchantCreation merchantCreation) {
        return MerchantEntity
                .builder()
                .name(merchantCreation.name())
                .email(merchantCreation.email())
                .password(merchantCreation.password())
                .build();
    }
}
