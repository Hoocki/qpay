package com.qpay.transactionhistorymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class TransactionHistoryManagerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TransactionHistoryManagerApplication.class, args);
    }

}
