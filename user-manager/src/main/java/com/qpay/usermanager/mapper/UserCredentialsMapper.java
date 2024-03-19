package com.qpay.usermanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsCreation;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsModification;
import com.qpay.usermanager.model.dto.customer.CustomerCreation;
import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.dto.merchant.MerchantModification;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsMapper {

    public UserCredentialsCreation mapCustomerCreation(final CustomerCreation customerCreation) {
        return UserCredentialsCreation.builder()
                .email(customerCreation.email())
                .password(customerCreation.password())
                .userType(UserType.CUSTOMER)
                .build();
    }

    public UserCredentialsModification mapCustomerModification(final CustomerModification customerModification) {
        return UserCredentialsModification.builder()
                .email(customerModification.email())
                .password(customerModification.password())
                .build();
    }

    public UserCredentialsCreation mapMerchantCreation(final MerchantCreation merchantCreation) {
        return UserCredentialsCreation.builder()
                .email(merchantCreation.email())
                .password(merchantCreation.password())
                .userType(UserType.MERCHANT)
                .build();
    }

    public UserCredentialsModification mapMerchantModification(final MerchantModification merchantModification) {
        return UserCredentialsModification.builder()
                .email(merchantModification.email())
                .password(merchantModification.password())
                .build();
    }
}