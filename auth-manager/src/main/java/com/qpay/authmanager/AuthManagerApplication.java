package com.qpay.authmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AuthManagerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AuthManagerApplication.class, args);
	}

}