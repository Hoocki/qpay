package com.qpay.authmanager.model.dto;

import com.qpay.libs.models.UserType;
import lombok.Builder;

@Builder
public record TokenData (
        long userId,

        String email,

        UserType userType
) {
}
