package com.qpay.transactionhistorymanager.service.impl;

import com.qpay.transactionhistorymanager.mapper.TransactionMapper;
import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.repository.TransactionRepository;
import com.qpay.transactionhistorymanager.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public List<TransactionEntity> getPageOfTransactionsByUserId(final long userId, final Pageable pageable) {
        return transactionRepository.findAllByIdFromOrIdToOrderByCreatedAt(userId, userId, pageable);
    }

    public TransactionEntity saveTransaction(final TransactionModification transactionModification) {
        final var transactionEntity = transactionMapper.map(transactionModification);

        return transactionRepository.save(transactionEntity);
    }
}
