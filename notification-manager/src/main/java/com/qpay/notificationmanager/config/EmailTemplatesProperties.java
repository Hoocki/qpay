package com.qpay.notificationmanager.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email-template")
@Data
public class EmailTemplatesProperties {

    private TemplateMessage payment;

    @Data
    @Builder
    public static class TemplateMessage {

        private String subject;

        private String text;

    }

}
