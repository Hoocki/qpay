package com.qpay.notificationmanager.event;

import com.qpay.libs.models.ReportNotification;
import com.qpay.notificationmanager.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportNotificationConsumer {

    private final NotificationService emailNotificationService;

    @KafkaListener(topics = "${broker.topics.report-topic}",
            groupId = "${broker.groups.transaction-group}")
    public void consumeReportEvent(final ReportNotification reportNotification) {
        log.info("PaymentEvent claimed: {}", reportNotification);
        emailNotificationService.sendMessage(reportNotification);
    }
}
