package com.qpay.transactionhistorymanager.repository;

import com.qpay.libs.models.TransactionType;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

    List<TransactionEntity> findAllByIdFromOrderByCreatedAt(long idFrom, Pageable pageable);

    List<TransactionEntity> findAllByIdToOrderByCreatedAt(long idTo, Pageable pageable);

    List<TransactionEntity> findAllByIdFromAndCreatedAtBetween(long idFrom, LocalDateTime from, LocalDateTime to);

    List<TransactionEntity> findAllByIdToAndCreatedAtBetween(long idTo, LocalDateTime from, LocalDateTime to);

    List<TransactionEntity> findAllByIdFromAndTransactionTypeAndCreatedAtBetween(long idFrom, TransactionType transactionType, LocalDateTime from, LocalDateTime to);
}
