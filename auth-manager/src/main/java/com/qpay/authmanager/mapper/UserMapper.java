package com.qpay.authmanager.mapper;

import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity map(final UserModification userModification) {
        return UserEntity.builder()
                .email(userModification.email())
                .userType(userModification.userType())
                .build();
    }
}
