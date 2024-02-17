package com.qpay.paymentmanager.event;

import com.qpay.libs.models.PaymentNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentNotificationProducer {

    private final KafkaTemplate<String, PaymentNotification> kafkaTemplate;

    @Value("${broker.topics.payment-topic}")
    private String paymentTopic;

    public void publishPaymentNotification(final PaymentNotification paymentNotification) {
        kafkaTemplate.send(paymentTopic, paymentNotification.emailTo(), paymentNotification);
        log.info("Payment notification event sent: {}", paymentNotification);
    }

}