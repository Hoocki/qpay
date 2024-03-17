package com.qpay.authmanager.controller;

import com.qpay.authmanager.model.dto.AuthCredentials;
import com.qpay.authmanager.model.dto.JwtAuthenticationResponse;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.authmanager.service.authentication.AuthenticationService;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationService authenticationService;

    @Test
    void should_signInUser_when_UserDataCorrect() {
        // given
        var authCredentials = AuthCredentials
                .builder()
                .email("user@mail.com")
                .password("password")
                .build();

        var userEntity = UserCredentialsEntity
                .builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .password("password")
                .build();

        var expectedToken = JwtAuthenticationResponse
                .builder()
                .token(userEntity.toString())
                .build();

        given(authenticationService.signIn(authCredentials)).willReturn(expectedToken);

        // when
        var result = authController.signIn(authCredentials);

        // then
        assertThat(result).isEqualTo(expectedToken);
    }
}