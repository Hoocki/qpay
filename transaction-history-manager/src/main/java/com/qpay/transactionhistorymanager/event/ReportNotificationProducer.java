package com.qpay.transactionhistorymanager.event;

import com.qpay.libs.models.ReportNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReportNotificationProducer {

    private final KafkaTemplate<String, ReportNotification> kafkaTemplate;

    @Value("${broker.topics.report-topic}")
    private String paymentTopic;

    public void publishReportNotification(final ReportNotification reportNotification) {
        kafkaTemplate.send(paymentTopic, reportNotification.email(), reportNotification);
        log.info("Payment notification event sent: {}", reportNotification);
    }

}
