package com.qpay.notificationmanager.event;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentNotificationConsumer {

    private final NotificationService emailNotificationService;

    @KafkaListener(topics = "${broker.topics.payment-topic}",
            groupId = "${broker.groups.payment-group}")
    public void consumePaymentEvent(final PaymentNotification paymentNotification) {
        log.info("PaymentEvent claimed: {}", paymentNotification);
        emailNotificationService.sendMessage(paymentNotification);
    }
}