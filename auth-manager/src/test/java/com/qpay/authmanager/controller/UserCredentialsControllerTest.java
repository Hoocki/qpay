package com.qpay.authmanager.controller;

import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.dto.UserCredentialsModification;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.authmanager.service.user.UserCredentialsService;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserCredentialsControllerTest {

    @InjectMocks
    private UserCredentialsController userCredentialsController;

    @Mock
    private UserCredentialsService userCredentialsService;

    private static final UserCredentialsEntity USER_CUSTOMER = UserCredentialsEntity
            .builder()
            .email("user@mail.com")
            .userType(UserType.CUSTOMER)
            .password("password")
            .build();

    private static final UserCredentialsModification USER_MODIFICATION = UserCredentialsModification
            .builder()
            .email("user@mail.com")
            .password("password")
            .build();

    private static final UserCredentialsCreation USER_CREATION = UserCredentialsCreation
            .builder()
            .email("user@mail.com")
            .userType(UserType.CUSTOMER)
            .password("password")
            .build();

    private static final String EMAIL = "user@mail.com";

    @Test
    void should_addUser() {
        // given
        given(userCredentialsService.addUser(USER_CREATION)).willReturn(USER_CUSTOMER);

        // when
        var result = userCredentialsController.addUser(USER_CREATION);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);
    }

    @Test
    void should_updateUser() {
        // given
        var oldEmail = "oldUser@mail.com";
        given(userCredentialsService.updateUser(oldEmail, USER_MODIFICATION)).willReturn(USER_CUSTOMER);

        // when
        var result = userCredentialsController.updateUser(oldEmail, USER_MODIFICATION);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);
    }

    @Test
    void should_deleteUser() {
        // when
        userCredentialsController.deleteUser(EMAIL);

        // then
        then(userCredentialsService).should().deleteUser(EMAIL);

    }
}