package com.qpay.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(final WebClientProperties webClientProperties) {
        return WebClient
                .builder()
                .baseUrl(webClientProperties.getUserManager().getBaseUrl())
                .build();
    }
}