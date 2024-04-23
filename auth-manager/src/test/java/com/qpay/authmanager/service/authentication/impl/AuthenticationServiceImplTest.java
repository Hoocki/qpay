package com.qpay.authmanager.service.authentication.impl;

import com.qpay.authmanager.mapper.TokenDataMapper;
import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.dto.TokenData;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.authmanager.service.exception.UserCredentialsNotFoundException;
import com.qpay.authmanager.service.jwt.JwtService;
import com.qpay.authmanager.service.user.UserCredentialsService;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserCredentialsService userCredentialsService;

    @Mock
    private TokenDataMapper tokenDataMapper;

    @Test
    @Disabled
    void should_signInUser_when_userExists() {
        // given
        var authCredentials = AuthCredentials
                .builder()
                .email("user@mail.com")
                .password("password")
                .build();

        var userEntity = UserCredentialsEntity
                .builder()
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

        given(userCredentialsService.getUserByEmail(authCredentials.email())).willReturn(userEntity);
        given(tokenDataMapper.map(userEntity)).willReturn(tokenData);
        given(jwtService.generateToken(tokenData)).willReturn(fakeToken);

        // when
        var result = authenticationService.signIn(authCredentials);

        // then
        assertThat(result).isEqualTo(expectedToken);

    }

    @Test
    void should_throwUserNotFoundException_when_userDoesNotExist() {
        // given
        var authCredentials = AuthCredentials
                .builder()
                .email("user@mail.com")
                .password("password")
                .build();

        given(userCredentialsService.getUserByEmail(authCredentials.email())).willThrow(UserCredentialsNotFoundException.class);

        // when
        var thrown = catchThrowable(() -> authenticationService.signIn(authCredentials));

        // then
        assertThat(thrown).isInstanceOf(UserCredentialsNotFoundException.class);

    }
}