package com.qpay.authmanager.service.user.impl;

import com.qpay.authmanager.mapper.UserCredentialsMapper;
import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.dto.UserCredentialsModification;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.authmanager.repository.UserCredentialsRepository;
import com.qpay.authmanager.service.exception.UserCredentialsNotFoundException;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserCredentialsServiceImplTest {

    @InjectMocks
    private UserCredentialsServiceImpl userService;

    @Mock
    private UserCredentialsRepository userCredentialsRepository;

    @Mock
    private UserCredentialsMapper userCredentialsMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

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
        var password = "password";
        given(userCredentialsMapper.map(USER_CREATION)).willReturn(USER_CUSTOMER);
        given(passwordEncoder.encode(USER_MODIFICATION.password())).willReturn(password);
        given(userCredentialsRepository.save(USER_CUSTOMER)).willReturn(USER_CUSTOMER);

        // when
        var result = userService.addUser(USER_CREATION);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);
    }

    @Test
    void should_throwUserAlreadyExistsException_when_addExistingUser() {
        // given
        var userEntity = UserCredentialsEntity.builder()
                .email("user@mail.com")
                .userType(UserType.CUSTOMER)
                .build();
        given(userCredentialsMapper.map(USER_CREATION)).willReturn(userEntity);
        given(passwordEncoder.encode(USER_MODIFICATION.password())).willReturn(USER_CUSTOMER.getPassword());
        given(userCredentialsRepository.save(USER_CUSTOMER)).willThrow(DuplicateKeyException.class);

        // when
        var thrown = catchThrowable(() -> userService.addUser(USER_CREATION));

        // then
        assertThat(thrown).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void should_updateUser() {
        // given
        var email = "oldUser@mail.com";
        var oldUser = UserCredentialsEntity
                .builder()
                .email(email)
                .userType(UserType.CUSTOMER)
                .password("oldPassword")
                .build();
        given(userCredentialsRepository.findByEmail(email)).willReturn(oldUser);
        given(passwordEncoder.encode(USER_MODIFICATION.password())).willReturn(USER_CUSTOMER.getPassword());
        given(userCredentialsRepository.save(USER_CUSTOMER)).willReturn(USER_CUSTOMER);

        // when
        var result = userService.updateUser(email, USER_MODIFICATION);

        // then
        assertThat(result).isEqualTo(USER_CUSTOMER);

    }

    @Test
    void should_throwUserNotFoundException_when_userDoesNotExist() {
        // given
        var email = "oldUser@mail.com";
        given(userCredentialsRepository.findByEmail(email)).willReturn(null);

        // when
        var thrown = catchThrowable(() -> userService.updateUser(email, USER_MODIFICATION));

        // then
        assertThat(thrown).isInstanceOf(UserCredentialsNotFoundException.class);

    }

    @Test
    void should_deleteUser() {
        // given
        given(userCredentialsRepository.findByEmail(EMAIL)).willReturn(USER_CUSTOMER);

        // when
        userService.deleteUser(EMAIL);

        // then
        then(userCredentialsRepository).should().delete(USER_CUSTOMER);
    }

    @Test
    void should_throwUserNotFoundException_when_deletingNonExistingUser() {
        // given
        given(userCredentialsRepository.findByEmail(EMAIL)).willReturn(null);

        // when
        var thrown = catchThrowable(() -> userService.deleteUser(EMAIL));

        // then
        assertThat(thrown).isInstanceOf(UserCredentialsNotFoundException.class);
    }

    @Test
    void should_returnUserDetails_when_emailCorrect() {
        // given
        var expectedUser = User
                .builder()
                .username(EMAIL)
                .password("password")
                .authorities(new ArrayList<>())
                .build();

        given(userCredentialsRepository.findByEmail(EMAIL)).willReturn(USER_CUSTOMER);

        // when
        var result = userService.loadUserByUsername(EMAIL);

        // then
        assertThat(result).isEqualTo(expectedUser);

    }

}