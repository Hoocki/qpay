package com.qpay.notificationmanager.service.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.libs.models.ReportNotification;
import com.qpay.notificationmanager.generator.impl.MailMessageGenerator;
import com.qpay.notificationmanager.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "email.enabled", havingValue = "true")
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    private final MailMessageGenerator mailMessageGenerator;

    public void sendMessage(final PaymentNotification paymentNotification) {
        final var messageToMerchant = mailMessageGenerator.getPaymentMessageMerchant(paymentNotification);
        mailSender.send(messageToMerchant);
        log.info("Message for merchant sent to: {}", paymentNotification.emailTo());
        final var messageToCustomer = mailMessageGenerator.getPaymentMessageCustomer(paymentNotification);
        mailSender.send(messageToCustomer);
        log.info("Message for customer sent to: {}", paymentNotification.emailFrom());
    }

    public void sendMessage(final ReportNotification reportNotification) {
        final var message = mailMessageGenerator.getReportMessage(reportNotification);
        mailSender.send(message);
        log.info("Report sent to: {}", reportNotification.email());
    }
}
