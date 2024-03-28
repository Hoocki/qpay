package com.qpay.transactionhistorymanager.mapper;

import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity map(final TransactionModification transactionModification) {
        return TransactionEntity.builder()
                .idFrom(transactionModification.idFrom())
                .nameFrom(transactionModification.nameFrom())
                .idTo(transactionModification.idTo())
                .nameTo(transactionModification.nameTo())
                .amount(transactionModification.amount())
                .transactionType(transactionModification.transactionType())
                .build();
    }
}
