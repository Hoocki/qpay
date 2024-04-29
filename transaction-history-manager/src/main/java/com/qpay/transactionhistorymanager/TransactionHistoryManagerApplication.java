package com.qpay.transactionhistorymanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableFeignClients
public class TransactionHistoryManagerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TransactionHistoryManagerApplication.class, args);
    }

}
