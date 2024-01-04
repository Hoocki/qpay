package com.qpay.paymentmanager.repository;

import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.libs.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    WalletEntity findWalletEntityByUserIdAndUserType(long userId, UserType userType);
}
