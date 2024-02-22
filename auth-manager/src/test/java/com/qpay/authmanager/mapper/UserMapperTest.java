package com.qpay.authmanager.mapper;

import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void should_returnUserEntity_when_userModificationPassed() {
        // given
        var userEntity = UserEntity
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .build();

        var userModification = UserModification
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .build();

        // when
        var result = userMapper.map(userModification);

        // then
        assertThat(result).isEqualTo(userEntity);
    }
}