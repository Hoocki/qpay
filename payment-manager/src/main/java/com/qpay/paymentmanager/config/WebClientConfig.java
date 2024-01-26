package com.qpay.paymentmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    public static final String NOTIFICATION_WEB_CLIENT_NAME = "notificationWebClient";

    @Bean(name = NOTIFICATION_WEB_CLIENT_NAME)
    public WebClient notificationWebClient(final WebClientProperties webClientProperties) {
        return WebClient
                .builder()
                .baseUrl(webClientProperties.getNotificationManager().getBaseUrl())
                .build();
    }
}