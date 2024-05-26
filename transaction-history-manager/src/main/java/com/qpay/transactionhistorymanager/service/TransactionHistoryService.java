package com.qpay.transactionhistorymanager.service;

import com.qpay.libs.models.UserType;
import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.dto.TransactionOutcome;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionHistoryService {

    List<TransactionEntity> getPageOfTransactionsByUserId(long userId, final Pageable pageable, final UserType userType);

    TransactionEntity saveTransaction(TransactionModification transactionModification);

    TransactionOutcome getTransactionsOutcome(long walletId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}