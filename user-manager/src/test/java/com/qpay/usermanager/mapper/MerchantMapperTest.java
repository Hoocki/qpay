package com.qpay.usermanager.mapper;

import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MerchantMapperTest {

    private final MerchantMapper merchantMapper = new MerchantMapper();

    @Test
    void should_returnMerchantEntity_when_givenMerchantCreation() {
        //given
        MerchantCreation merchantCreation = MerchantCreation.builder().name("bob").email("bob@gmail.com").password("word").build();
        var expectedMerchantEntity = MerchantEntity.builder()
                .name("bob")
                .email("bob@gmail.com")
                .password("word")
                .build();

        //when
        var result = merchantMapper.map(merchantCreation);

        //then
        assertThat(result).isEqualTo(expectedMerchantEntity);
    }

}