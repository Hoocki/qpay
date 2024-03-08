package com.qpay.transactionhistorymanager.repository;

import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

    List<TransactionEntity> findAllByIdFromOrIdToOrderByCreatedAt(long idFrom, long idTo, Pageable pageable);
}
