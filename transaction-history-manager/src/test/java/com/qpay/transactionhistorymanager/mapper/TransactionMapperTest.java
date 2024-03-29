package com.qpay.transactionhistorymanager.mapper;

import com.qpay.transactionhistorymanager.model.TransactionType;
import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionMapperTest {

    private final TransactionMapper transactionMapper = new TransactionMapper();

    @Test
    void should_returnTransactionEntity_when_givenTransactionModification() {
        // given
        var transactionModification = TransactionModification.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .transactionType(TransactionType.PAYMENT)
                .build();

        //when
        var result = transactionMapper.map(transactionModification);

        //then
        var expectedTransactionEntity = TransactionEntity.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .transactionType(TransactionType.PAYMENT)
                .build();
        assertThat(result).isEqualTo(expectedTransactionEntity);
    }

}