package com.qpay.usermanager.client;

import com.qpay.usermanager.model.dto.authuser.UserCredentialsCreation;
import com.qpay.usermanager.model.dto.authuser.UserCredentialsModification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "auth")
public interface AuthenticationClient {

    String AUTHENTICATION_USER_PATH = "/api/v1/credentials";

    String USER_EMAIL = "/{email}";

    @PostMapping(value = AUTHENTICATION_USER_PATH)
    void createUserCredentials(UserCredentialsCreation userCredentialsCreation);

    @PutMapping(value = AUTHENTICATION_USER_PATH + USER_EMAIL)
    void updateUserCredentials(@PathVariable String email, UserCredentialsModification userCredentialsModification);

    @DeleteMapping(value = AUTHENTICATION_USER_PATH + USER_EMAIL)
    void deleteUserCredentials(@PathVariable String email);

}