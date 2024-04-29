package com.qpay.transactionhistorymanager.controller;

import com.qpay.libs.models.TransactionType;
import com.qpay.libs.models.UserType;
import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.service.TransactionHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionHistoryControllerTest {

    @InjectMocks
    private TransactionHistoryController transactionHistoryController;

    @Mock
    private TransactionHistoryService transactionHistoryService;

    @Test
    void should_getTransactions_when_pageProvided() {
        //given
        var transaction1 = TransactionEntity.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .transactionType(TransactionType.PAYMENT)
                .build();

        var transaction2 = TransactionEntity.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("c")
                .idTo(3)
                .amount(new BigDecimal(2))
                .transactionType(TransactionType.PAYMENT)
                .build();

        var list = List.of(transaction1, transaction2);
        var pageable = PageRequest.of(0, 2);
        given(transactionHistoryService.getPageOfTransactionsByUserId(1, pageable, UserType.CUSTOMER)).willReturn(list);

        //when
        var result = transactionHistoryController.getPageOfTransactionsByUserId(1, pageable, UserType.CUSTOMER);

        //then
        assertThat(result).isEqualTo(list);
    }

    @Test
    void should_saveTransaction() {
        //given
        var transactionModification = TransactionModification.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .transactionType(TransactionType.PAYMENT)
                .build();

        var transactionEntity = TransactionEntity.builder()
                .nameFrom("c")
                .idFrom(3)
                .nameTo("a")
                .idTo(1)
                .amount(new BigDecimal(2))
                .transactionType(TransactionType.PAYMENT)
                .build();

        given(transactionHistoryService.saveTransaction(transactionModification)).willReturn(transactionEntity);

        //when
        var result = transactionHistoryController.saveTransaction(transactionModification);

        //then
        assertThat(result).isEqualTo(transactionEntity);
    }
}