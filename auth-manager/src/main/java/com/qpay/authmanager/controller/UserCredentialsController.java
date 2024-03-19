package com.qpay.authmanager.controller;

import com.qpay.authmanager.model.dto.UserCredentialsCreation;
import com.qpay.authmanager.model.dto.UserCredentialsModification;
import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.authmanager.service.user.UserCredentialsService;
import com.qpay.authmanager.utility.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.BASE_USER_PATH)
public class UserCredentialsController {

    private final UserCredentialsService userCredentialsService;

    @PostMapping
    public UserCredentialsEntity addUser(@RequestBody final UserCredentialsCreation userCredentialsCreation) {
        return userCredentialsService.addUser(userCredentialsCreation);
    }

    @PutMapping(PathUtils.USER_EMAIL)
    public UserCredentialsEntity updateUser(@PathVariable final String email, @RequestBody final UserCredentialsModification userCredentialsModification) {
        return userCredentialsService.updateUser(email, userCredentialsModification);
    }

    @DeleteMapping(PathUtils.USER_EMAIL)
    public void deleteUser(@PathVariable final String email) {
        userCredentialsService.deleteUser(email);
    }
}
