package com.qpay.paymentmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentManagerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PaymentManagerApplication.class, args);
    }
}
