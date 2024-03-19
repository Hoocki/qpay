package com.qpay.usermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserManagerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}

}
