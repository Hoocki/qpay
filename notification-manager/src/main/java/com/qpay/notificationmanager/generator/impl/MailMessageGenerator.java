package com.qpay.notificationmanager.generator.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.config.EmailTemplatesProperties;
import com.qpay.notificationmanager.generator.MessageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class MailMessageGenerator implements MessageGenerator {

    private final EmailTemplatesProperties emailTemplate;

    public SimpleMailMessage getPaymentMessage(final PaymentNotification paymentNotification) {
        final var message = new SimpleMailMessage();
        final var templateMessage = emailTemplate.getPayment();
        final var text = format(templateMessage.getText(), paymentNotification.amount());
        message.setTo(paymentNotification.emailTo());
        message.setSubject(templateMessage.getSubject());
        message.setText(text);
        return message;
    }

}
