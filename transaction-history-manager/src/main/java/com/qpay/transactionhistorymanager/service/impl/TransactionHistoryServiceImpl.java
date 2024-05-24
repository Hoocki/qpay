package com.qpay.transactionhistorymanager.service.impl;

import com.qpay.libs.models.TransactionType;
import com.qpay.libs.models.UserType;
import com.qpay.transactionhistorymanager.aggregation.AggregationPipelineGenerator;
import com.qpay.transactionhistorymanager.mapper.TransactionMapper;
import com.qpay.transactionhistorymanager.model.dto.Outcome;
import com.qpay.transactionhistorymanager.model.dto.TransactionGroup;
import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.dto.TransactionOutcome;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.repository.TransactionRepository;
import com.qpay.transactionhistorymanager.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final AggregationPipelineGenerator aggregationPipelineGenerator;

    public List<TransactionEntity> getPageOfTransactionsByUserId(final long walletId, final Pageable pageable, final UserType userType) {
        if (userType == UserType.CUSTOMER) {
            return transactionRepository.findAllByIdFromOrderByCreatedAt(walletId, pageable);
        }
        return transactionRepository.findAllByIdToOrderByCreatedAt(walletId, pageable);
    }

    public TransactionEntity saveTransaction(final TransactionModification transactionModification) {
        final var transactionEntity = transactionMapper.map(transactionModification);
        return transactionRepository.save(transactionEntity);
    }

    public TransactionOutcome getTransactionsOutcome(final long walletId, final LocalDateTime startDate, final LocalDateTime endDate) {
        final var income = getIncome(walletId, startDate, endDate);
        final var expenses = getExpenses(walletId, startDate, endDate);
        return TransactionOutcome.builder()
                .income(income)
                .expenses(expenses)
                .build();
    }

    private Outcome getExpenses(final long walletId, final LocalDateTime startDate, final LocalDateTime endDate) {
        final var expenses = aggregationPipelineGenerator.generatePipeline(walletId, startDate, endDate);
        return addOthersGroup(expenses);
    }

    private Outcome addOthersGroup(final Outcome expenses) {
        final var totalGroupAmount = expenses.transactionsGroup().stream()
                .map(TransactionGroup::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalGroupAmount.compareTo(expenses.total()) < 0) {
            final var othersAmount = expenses.total().subtract(totalGroupAmount);
            final var othersShare = BigDecimal.valueOf(100).subtract(
                    expenses.transactionsGroup().stream()
                            .map(TransactionGroup::share)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
            );
            final var othersGroup = TransactionGroup.builder()
                    .amount(othersAmount)
                    .destination("Others")
                    .share(othersShare)
                    .build();
            expenses.transactionsGroup().add(othersGroup);
        }
        return expenses;
    }

    private Outcome getIncome(final long walletId, final LocalDateTime startDate, final LocalDateTime endDate) {
        final var transactionsList = transactionRepository.findAllByIdFromAndTransactionTypeAndCreatedAtBetween(walletId, TransactionType.TOP_UP, startDate, endDate);
        final var totalIncome = transactionsList.stream()
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return Outcome.builder()
                .total(totalIncome)
                .transactionsGroup(List.of())
                .build();
    }
}