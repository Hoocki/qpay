package com.qpay.authmanager.service.impl;

import com.qpay.authmanager.mapper.UserMapper;
import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.repository.UserRepository;
import com.qpay.authmanager.service.UserService;
import com.qpay.authmanager.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserEntity addUser(final UserModification userModification) {
        final var newUserEntity = userMapper.map(userModification);
        newUserEntity.setPassword(passwordEncoder.encode(userModification.password()));
        return userRepository.save(newUserEntity);
    }

    public UserEntity updateUser(final UserModification userModification, final String email) {
        final var userEntity = getUserByEmail(email);
        userEntity.setEmail(userModification.email());
        userEntity.setPassword(passwordEncoder.encode(userModification.password()));
        return userRepository.save(userEntity);
    }

    public void deleteUser(final String email) {
        final var userEntity = getUserByEmail(email);
        userRepository.delete(userEntity);
    }

    private UserEntity getUserByEmail(final String email) {
        final var userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UserNotFoundException();
        }
        return userEntity;
    }
}
