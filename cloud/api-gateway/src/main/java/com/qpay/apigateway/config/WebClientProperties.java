package com.qpay.apigateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webclient.services")
@Data
public class WebClientProperties {

    private ServiceUrl userManager;

    @Data
    public static class ServiceUrl {

        private String baseUrl;
    }
}
