package com.qpay.paymentmanager.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webclient.services")
@Data
public class WebClientProperties {

    private ServiceUrl notificationManager;

    private ServiceUrl transactionManager;


    @Data
    public static class ServiceUrl {

        private String baseUrl;

    }
}
