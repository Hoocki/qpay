package com.qpay.notificationmanager.service.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.generator.impl.MailMessageGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;

import static java.lang.String.format;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EmailNotificationServiceTest {

    @InjectMocks
    private EmailNotificationService emailNotificationService;

    @Mock
    private MailMessageGenerator mailMessageGenerator;

    @Mock
    private JavaMailSender mailSender;

    private static final PaymentNotification PAYMENT_NOTIFICATION = PaymentNotification
            .builder()
            .emailFrom("user1@gmail.com")
            .amount(BigDecimal.valueOf(10))
            .emailTo("user2gmail.com")
            .build();

    @Test
    void should_sendMessage_when_receivePaymentNotification() {
        // given
        var message = new SimpleMailMessage();
        final var text = format("You received a payment of %s. Thank you for using our service.", PAYMENT_NOTIFICATION.amount());
        message.setTo(PAYMENT_NOTIFICATION.emailTo());
        message.setSubject("Payment Notification");
        message.setText(text);
        given(mailMessageGenerator.getPaymentMessage(PAYMENT_NOTIFICATION)).willReturn(message);

        // when
        emailNotificationService.sendMessage(PAYMENT_NOTIFICATION);

        // then
        then(mailSender).should().send(message);
    }
}