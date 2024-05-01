package com.qpay.notificationmanager.generator.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.libs.models.ReportNotification;
import com.qpay.notificationmanager.config.EmailTemplatesProperties;
import com.qpay.notificationmanager.generator.MessageGenerator;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import java.io.File;

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

    public MimeMessagePreparator getReportMessage(final ReportNotification reportNotification) {
        return mimeMessage -> {
            final var templateMessage = emailTemplate.getReport();
            final var text = format(templateMessage.getText(), reportNotification.dateFrom(), reportNotification.dateTo());
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(reportNotification.email()));
            mimeMessage.setSubject(templateMessage.getSubject());

            final var file = new File(reportNotification.filePath() + reportNotification.fileName());
            final var fileSystemResource = new FileSystemResource(file);
            final var mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.addAttachment(reportNotification.fileName(), fileSystemResource);
            mimeMessageHelper.setText(text);
        };
    }

}
