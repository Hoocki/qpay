package com.qpay.authmanager.service.user.impl;

import com.qpay.authmanager.mapper.UserMapper;
import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.repository.UserRepository;
import com.qpay.authmanager.service.exception.UserNotFoundException;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

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
        var password = "password";
        given(userMapper.map(USER_MODIFICATION)).willReturn(USER_CUSTOMER);
        given(passwordEncoder.encode(USER_MODIFICATION.password())).willReturn(password);
        given(userRepository.save(USER_CUSTOMER)).willReturn(USER_CUSTOMER);

        // when
        var result = userService.addUser(USER_MODIFICATION);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);
    }

    @Test
    void should_throwUserAlreadyExistsException_when_addExistingUser() {
        // given
        var userEntity = UserEntity.builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .build();
        given(userMapper.map(USER_MODIFICATION)).willReturn(userEntity);
        given(passwordEncoder.encode(USER_MODIFICATION.password())).willReturn(USER_CUSTOMER.getPassword());
        given(userRepository.save(USER_CUSTOMER)).willThrow(DuplicateKeyException.class);

        // when
        var thrown = catchThrowable(() -> userService.addUser(USER_MODIFICATION));

        // then
        assertThat(thrown).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void should_updateUser() {
        // given
        var email = "oldUser@mail.com";
        var oldUser = UserEntity
                .builder()
                .email(email)
                .userType(UserType.CUSTOMER)
                .password("oldPassword")
                .build();
        given(userRepository.findByEmail(email)).willReturn(oldUser);
        given(passwordEncoder.encode(USER_MODIFICATION.password())).willReturn(USER_CUSTOMER.getPassword());
        given(userRepository.save(USER_CUSTOMER)).willReturn(USER_CUSTOMER);

        // when
        var result = userService.updateUser(USER_MODIFICATION, email);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);

    }

    @Test
    void should_throwUserNotFoundException_when_userDoesNotExist() {
        // given
        var email = "oldUser@mail.com";
        given(userRepository.findByEmail(email)).willReturn(null);

        // when
        var thrown = catchThrowable(() -> userService.updateUser(USER_MODIFICATION, email));

        // then
        assertThat(thrown).isInstanceOf(UserNotFoundException.class);

    }

    @Test
    void should_deleteUser() {
        // given
        given(userRepository.findByEmail(EMAIL)).willReturn(USER_CUSTOMER);

        // when
        userService.deleteUser(EMAIL);

        // then
        then(userRepository).should().delete(USER_CUSTOMER);
    }

    @Test
    void should_throwUserNotFoundException_when_deletingNonExistingUser() {
        // given
        given(userRepository.findByEmail(EMAIL)).willReturn(null);

        // when
        var thrown = catchThrowable(() -> userService.deleteUser(EMAIL));

        // then
        assertThat(thrown).isInstanceOf(UserNotFoundException.class);
    }
}