package com.qpay.authmanager.service.user.impl;

import com.qpay.authmanager.mapper.UserMapper;
import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.repository.UserRepository;
import com.qpay.authmanager.service.user.UserService;
import com.qpay.authmanager.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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

    public UserEntity getUserByEmail(final String email) {
        final var userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UserNotFoundException();
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
