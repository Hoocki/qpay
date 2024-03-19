package com.qpay.authmanager.repository;

import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import com.qpay.libs.models.UserType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Disabled
class UserCredentialsRepositoryTest {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void should_findUserByEmail() {
        var email = "user@mail.com";
        var userType = UserType.CUSTOMER;
        var user =
                UserCredentialsEntity.builder()
                        .email(email)
                        .userType(userType)
                        .password("password")
                        .build();
        var expectedUser = userCredentialsRepository.save(user);

        // when
        var result = userCredentialsRepository.findByEmail(email);

        // then
        assertThat(result).isEqualTo(expectedUser);
    }
}