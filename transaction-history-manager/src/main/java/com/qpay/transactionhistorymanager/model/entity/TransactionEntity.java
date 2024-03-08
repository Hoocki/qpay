package com.qpay.transactionhistorymanager.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
@Builder
public class TransactionEntity {

    @Id
    private String id;

    private String nameFrom;

    private long idFrom;

    private String nameTo;

    private long idTo;

    private BigDecimal amount;

    @CreatedDate
    private LocalDateTime createdAt;

}
