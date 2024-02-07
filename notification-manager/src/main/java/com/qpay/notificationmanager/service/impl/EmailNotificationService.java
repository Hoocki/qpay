package com.qpay.notificationmanager.service.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.generator.impl.MailMessageGenerator;
import com.qpay.notificationmanager.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    private final MailMessageGenerator mailMessageGenerator;

    public void sendMessage(final PaymentNotification paymentNotification) {
        final var message = mailMessageGenerator.getPaymentMessage(paymentNotification);
        mailSender.send(message);
    }

}
