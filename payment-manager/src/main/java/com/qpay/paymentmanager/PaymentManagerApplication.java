package com.qpay.paymentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PaymentManagerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PaymentManagerApplication.class, args);
    }

}
