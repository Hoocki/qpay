package com.qpay.usermanager.mapper;

import com.qpay.libs.models.UserType;
import com.qpay.usermanager.model.dto.wallet.WalletCreation;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WalletMapperTest {

    private final WalletMapper walletMapper = new WalletMapper();

    @Test
    void should_returnWalletCreation_when_givenCustomerEntity() {
        // given
        var customerEntity = CustomerEntity.builder()
                .name("customer")
                .id(1L)
                .email("customer@mail.com")
                .build();

        // when
        var result = walletMapper.map(customerEntity);

        // then
        var expectedWallet = WalletCreation.builder()
                .userId(1L)
                .name("customer")
                .userType(UserType.CUSTOMER)
                .build();
        assertThat(result).isEqualTo(expectedWallet);
    }

    @Test
    void should_returnWalletCreation_when_givenMerchantEntity() {
        // given
        var merchantEntity = MerchantEntity.builder()
                .name("merchant")
                .id(1L)
                .email("merchant@mail.com")
                .build();

        // when
        var result = walletMapper.map(merchantEntity);

        // then
        var expectedWallet = WalletCreation.builder()
                .userId(1L)
                .name("merchant")
                .userType(UserType.MERCHANT)
                .build();
        assertThat(result).isEqualTo(expectedWallet);
    }
}