package com.qpay.usermanager.mapper;

import com.qpay.libs.models.MerchantReportInfo;
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
                .build();
    }

    public MerchantReportInfo map(final MerchantEntity merchant) {
        return MerchantReportInfo.builder()
                .email(merchant.getEmail())
                .name(merchant.getName())
                .build();
    }
}
