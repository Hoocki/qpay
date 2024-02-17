package com.qpay.notificationmanager.service.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.generator.impl.MailMessageGenerator;
import com.qpay.notificationmanager.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    private final MailMessageGenerator mailMessageGenerator;

    public void sendMessage(final PaymentNotification paymentNotification) {
        final var message = mailMessageGenerator.getPaymentMessage(paymentNotification);
        mailSender.send(message);
        log.info("Message sent to: {}", paymentNotification.emailTo());
    }

}
