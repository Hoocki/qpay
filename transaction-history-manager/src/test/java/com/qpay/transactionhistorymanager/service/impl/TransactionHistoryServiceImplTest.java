package com.qpay.transactionhistorymanager.service.impl;

import com.qpay.transactionhistorymanager.mapper.TransactionMapper;
import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TransactionHistoryServiceImplTest {

    @InjectMocks
    private TransactionHistoryServiceImpl transactionHistoryService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Test
    void should_getPageOfTransactionsByUserId() {
        //given
        var transaction1 = TransactionEntity.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .build();

        var transaction2 = TransactionEntity.builder()
                .nameFrom("c")
                .idFrom(3)
                .nameTo("a")
                .idTo(1)
                .amount(new BigDecimal(2))
                .build();
        var list = List.of(transaction1, transaction2);
        var pageable = PageRequest.of(0, 2);
        given(transactionRepository.findAllByIdFromOrIdToOrderByCreatedAt(1, 1, pageable))
                .willReturn(list);

        //when
        var result = transactionHistoryService.getPageOfTransactionsByUserId(1, pageable);

        //then
        assertThat(result).isEqualTo(list);
    }

    @Test
    void should_saveTransaction() {
        // given
        var transactionModification = TransactionModification.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .build();

        var transactionEntity = TransactionEntity.builder()
                .nameFrom("c")
                .idFrom(3)
                .nameTo("a")
                .idTo(1)
                .amount(new BigDecimal(2))
                .build();

        given(transactionMapper.map(transactionModification)).willReturn(transactionEntity);
        given(transactionRepository.save(transactionEntity)).willReturn(transactionEntity);

        // when
        var result = transactionHistoryService.saveTransaction(transactionModification);

        // then
        assertThat(result).isEqualTo(transactionEntity);
    }

}