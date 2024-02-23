package com.qpay.authmanager.service.jwt.impl;

import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Test
    void should_generateTokenBasedUserData() {
        // given
        var userEntity = UserEntity.builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .password("password")
                .build();

        var expectedToken = JwtAuthenticationResponse
                .builder()
                .token(userEntity.toString())
                .build();

        // when
        var result = jwtService.generate(userEntity);

        // then
        assertThat(result).isEqualTo(expectedToken);
    }

}