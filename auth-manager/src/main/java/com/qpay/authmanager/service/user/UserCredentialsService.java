package com.qpay.authmanager.service.user;

import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.dto.UserCredentialsModification;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;

public interface UserCredentialsService {

    UserCredentialsEntity getUserByEmail(String email);

    UserCredentialsEntity addUser(UserCredentialsCreation userCredentialsCreation);

    UserCredentialsEntity updateUser(String email, UserCredentialsModification userCredentialsModification);

    void deleteUser(String email);
}
