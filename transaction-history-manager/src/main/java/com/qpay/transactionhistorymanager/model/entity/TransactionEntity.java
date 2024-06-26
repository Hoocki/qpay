package com.qpay.transactionhistorymanager.model.entity;

import com.qpay.libs.models.TransactionType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;

    private TransactionType transactionType;

    @CreatedDate
    private LocalDateTime createdAt;

}
