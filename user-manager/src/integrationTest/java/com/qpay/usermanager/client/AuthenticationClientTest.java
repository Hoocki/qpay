package com.qpay.usermanager.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.qpay.libs.models.UserType;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsCreation;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsModification;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.qpay.usermanager.client.AuthenticationClient.AUTHENTICATION_USER_PATH;

@Disabled
@SpringBootTest
@WireMockTest(httpPort = 8186)
class AuthenticationClientTest {

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private ObjectMapper objectMapper;

    private static final UserCredentialsCreation USER_CREDENTIALS_CREATION = UserCredentialsCreation.builder()
            .email("user10mail.com")
            .password("password")
            .userType(UserType.CUSTOMER)
            .build();

    private static final UserCredentialsModification USER_CREDENTIALS_MODIFICATION = UserCredentialsModification.builder()
            .email("user20mail.com")
            .password("password1234")
            .build();

    @Test
    void should_returnOk_whenSendNewUserSuccessfully() throws JsonProcessingException {
        // given

        // when
        stubFor(post(urlPathEqualTo(AUTHENTICATION_USER_PATH))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(USER_CREDENTIALS_CREATION)))
                .willReturn(ok()));

        authenticationClient.createUserCredentials(USER_CREDENTIALS_CREATION);
    }

    @Test
    void should_returnOk_whenSendUpdatedUserSuccessfully() throws JsonProcessingException {
        // given
        var previousEmail = "user10mail.com";

        // when
        stubFor(put(urlPathEqualTo(AUTHENTICATION_USER_PATH + previousEmail))
                .withRequestBody(equalToJson(objectMapper.writeValueAsString(USER_CREDENTIALS_MODIFICATION)))
                .willReturn(ok()));

        authenticationClient.updateUserCredentials(previousEmail, USER_CREDENTIALS_MODIFICATION);
    }

    @Test
    void should_returnOk_whenDeleteUserSuccessfully() {
        // given
        var previousEmail = "user10mail.com";

        // when
        stubFor(delete(urlPathEqualTo(AUTHENTICATION_USER_PATH + previousEmail))
                .willReturn(ok()));

        authenticationClient.deleteUserCredentials(previousEmail);
    }
}