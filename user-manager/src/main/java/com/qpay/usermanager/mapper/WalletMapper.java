package com.qpay.usermanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.usermanager.model.dto.wallet.WalletCreation;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    private static final String DEFAULT_NAME_WALLET = "wallet";

    public WalletCreation map(final CustomerEntity customerEntity) {
        return WalletCreation.builder()
                .name(DEFAULT_NAME_WALLET)
                .userId(customerEntity.getId())
                .userType(UserType.CUSTOMER)
                .build();
    }

    public WalletCreation map(final MerchantEntity merchantEntity) {
        return WalletCreation.builder()
                .name(DEFAULT_NAME_WALLET)
                .userId(merchantEntity.getId())
                .userType(UserType.MERCHANT)
                .build();
    }
}
