package com.qpay.authmanager.controller;

import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.service.user.UserService;
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
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private static final UserEntity USER_CUSTOMER = UserEntity
            .builder()
            .email("user@mail.com")
            .userType(UserType.CUSTOMER)
            .password("password")
            .build();

    private static final UserModification USER_MODIFICATION = UserModification
            .builder()
            .email("user@mail.com")
            .userType(UserType.CUSTOMER)
            .password("password")
            .build();

    private static final String EMAIL = "user@mail.com";

    @Test
    void should_addUser() {
        // given
        given(userService.addUser(USER_MODIFICATION)).willReturn(USER_CUSTOMER);

        // when
        var result = userController.addUser(USER_MODIFICATION);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);
    }

    @Test
    void should_updateUser() {
        // given
        var oldEmail = "oldUser@mail.com";
        given(userService.updateUser(USER_MODIFICATION, oldEmail)).willReturn(USER_CUSTOMER);

        // when
        var result = userController.updateUser(USER_MODIFICATION, oldEmail);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);
    }

    @Test
    void should_deleteUser() {
        // when
        userController.deleteUser(EMAIL);

        // then
        then(userService).should().deleteUser(EMAIL);

    }
}