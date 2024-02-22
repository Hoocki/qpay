package com.qpay.authmanager.service.authentication.impl;

import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.service.exception.UserNotFoundException;
import com.qpay.authmanager.service.jwt.JwtService;
import com.qpay.authmanager.service.user.UserService;
import com.qpay.libs.models.UserType;
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
    private UserService userService;

    @Test
    void should_signInUser_when_userExists() {
        // given
        var authCredentials = AuthCredentials
                .builder()
                .email("user@mail.com")
                .password("password")
                .build();

        var userEntity = UserEntity
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .password("password")
                .build();

        var expectedToken = JwtAuthenticationResponse
                .builder()
                .token(userEntity.toString())
                .build();

        given(userService.getUserByEmail(authCredentials.email())).willReturn(userEntity);
        given(jwtService.generate(userEntity)).willReturn(expectedToken);

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

        given(userService.getUserByEmail(authCredentials.email())).willThrow(UserNotFoundException.class);

        // when
        var thrown = catchThrowable(() -> authenticationService.signIn(authCredentials));

        // then
        assertThat(thrown).isInstanceOf(UserNotFoundException.class);

    }
}