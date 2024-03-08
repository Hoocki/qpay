package com.qpay.transactionhistorymanager.service;

import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionHistoryService {

    List<TransactionEntity> getPageOfTransactionsByUserId(long userId, final Pageable pageable);

    TransactionEntity saveTransaction(TransactionModification transactionModification);
}
