package com.qpay.authmanager.service.user;

import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;

public interface UserService {

    UserEntity getUserByEmail(String email);

    UserEntity addUser(UserModification userModification);

    UserEntity updateUser(UserModification userModification, String email);

    void deleteUser(String email);
}
