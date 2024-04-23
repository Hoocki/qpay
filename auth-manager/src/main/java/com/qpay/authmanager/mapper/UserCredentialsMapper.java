package com.qpay.authmanager.mapper;

import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsMapper {

    public UserCredentialsEntity map(final UserCredentialsCreation userCredentialsCreation) {
        return UserCredentialsEntity.builder()
                .email(userCredentialsCreation.email())
                .userType(userCredentialsCreation.userType())
                .userId(userCredentialsCreation.userId())
                .build();
    }

}
