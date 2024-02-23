package com.qpay.authmanager.controller;

import com.qpay.authmanager.model.dto.UserModification;
import com.qpay.authmanager.model.entity.UserEntity;
import com.qpay.authmanager.service.user.UserService;
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
@RequestMapping(path = PathUtils.USER_PATH)
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserEntity addUser(@RequestBody final UserModification userModification) {
        return userService.addUser(userModification);
    }

    @PutMapping("{email}")
    public UserEntity updateUser(@RequestBody final UserModification userModification,
                                 @PathVariable final String email) {
        return userService.updateUser(userModification, email);
    }

    @DeleteMapping("{email}")
    public void deleteUser(@PathVariable final String email) {
        userService.deleteUser(email);
    }
}
