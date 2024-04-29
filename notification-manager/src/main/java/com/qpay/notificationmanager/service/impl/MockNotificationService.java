package com.qpay.notificationmanager.service.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.libs.models.ReportNotification;
import com.qpay.notificationmanager.service.NotificationService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
@ConditionalOnProperty(name = "email.enabled", havingValue = "false")
public class MockNotificationService implements NotificationService {

    public void sendMessage(final PaymentNotification paymentNotification) {
        log.info("Mock email message sent to email: {}", paymentNotification.emailTo());
    }

    @Override
    public void sendMessage(final ReportNotification reportNotification) {
        log.info("Mock email message sent to email: {}", reportNotification.email());
    }

}
