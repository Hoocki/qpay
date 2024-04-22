package com.qpay.authmanager.service.jwt.impl;

import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.dto.TokenData;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Disabled
class JwtServiceImplTest {

    @InjectMocks
    private JwtServiceImpl jwtService;

    @Test
    void should_generateTokenBasedUserData() {
        // given
        var userEntity = UserCredentialsEntity.builder()
                .userId(1L)
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .password("password")
                .build();

        var tokenData = TokenData
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .userId(1L)
                .build();

        var expectedToken = JwtAuthenticationResponse
                .builder()
                .token(userEntity.toString())
                .build();

        var fakeToken = userEntity.getEmail();

        // when
        var result = jwtService.generateToken(tokenData);

        // then
        assertThat(result).isEqualTo(fakeToken);
    }

}