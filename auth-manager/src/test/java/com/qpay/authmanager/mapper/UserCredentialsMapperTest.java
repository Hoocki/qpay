package com.qpay.authmanager.mapper;

import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialsMapperTest {

    private final UserCredentialsMapper userCredentialsMapper = new UserCredentialsMapper();

    @Test
    void should_returnUserEntity_when_userModificationPassed() {
        // given
        var userEntity = UserCredentialsEntity
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .build();

        var userCreation = UserCredentialsCreation
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .build();

        // when
        var result = userCredentialsMapper.map(userCreation);

        // then
        assertThat(result).isEqualTo(userEntity);
    }
}