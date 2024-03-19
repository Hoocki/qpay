package com.qpay.authmanager.service.user.impl;

import com.qpay.authmanager.mapper.UserCredentialsMapper;
import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.dto.UserCredentialsModification;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.authmanager.repository.UserCredentialsRepository;
import com.qpay.authmanager.service.user.UserCredentialsService;
import com.qpay.authmanager.service.exception.UserCredentialsNotFoundException;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserCredentialsServiceImpl implements UserCredentialsService, UserDetailsService {

    private final UserCredentialsRepository userCredentialsRepository;

    private final UserCredentialsMapper userCredentialsMapper;

    private final PasswordEncoder passwordEncoder;

    public UserCredentialsEntity addUser(final UserCredentialsCreation userCredentialsCreation) {
        final var newUserEntity = userCredentialsMapper.map(userCredentialsCreation);
        newUserEntity.setPassword(passwordEncoder.encode(userCredentialsCreation.password()));
        return userCredentialsRepository.save(newUserEntity);
    }

    public UserCredentialsEntity updateUser(final String email, final UserCredentialsModification userCredentialsModification) {
        final var userEntity = getUserByEmail(email);
        userEntity.setEmail(userCredentialsModification.email());
        if (StringUtils.isNotEmpty(userCredentialsModification.password())) {
            userEntity.setPassword(passwordEncoder.encode(userCredentialsModification.password()));
        }
        return userCredentialsRepository.save(userEntity);
    }

    public void deleteUser(final String email) {
        final var userEntity = getUserByEmail(email);
        userCredentialsRepository.delete(userEntity);
    }

    public UserCredentialsEntity getUserByEmail(final String email) {
        final var userEntity = userCredentialsRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UserCredentialsNotFoundException();
        }
        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final var userEntity = getUserByEmail(email);
        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                new ArrayList<>()
        );
    }
}
