package com.qpay.usermanager.repository;

import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {

    boolean existsByEmail(String email);
}
