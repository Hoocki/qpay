package com.qpay.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveryApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }

}
