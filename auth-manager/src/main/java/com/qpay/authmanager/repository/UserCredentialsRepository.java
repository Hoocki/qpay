package com.qpay.authmanager.repository;

import com.qpay.authmanager.model.entity.UserCredentialsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends MongoRepository<UserCredentialsEntity, String> {

    UserCredentialsEntity findByEmail(String email);
}
