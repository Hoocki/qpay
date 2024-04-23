package com.qpay.authmanager.mapper;

import com.qpay.authmanager.model.dto.TokenData;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import org.springframework.stereotype.Component;

@Component
public class TokenDataMapper {

    public TokenData map (final UserCredentialsEntity userCredentialsEntity) {
        return TokenData.builder()
                .email(userCredentialsEntity.getEmail())
                .userType(userCredentialsEntity.getUserType())
                .userId(userCredentialsEntity.getUserId())
                .build();
    }

}
