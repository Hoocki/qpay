package com.qpay.notificationmanager.config;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.generator.impl.MailMessageGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MailMessageGeneratorTest {

    @InjectMocks
    private MailMessageGenerator mailMessageGenerator;

    @Mock
    private EmailTemplatesProperties emailTemplate;

    private static final PaymentNotification PAYMENT_NOTIFICATION = PaymentNotification
            .builder()
            .emailFrom("user1@gmail.com")
            .amount(BigDecimal.valueOf(10))
            .emailTo("user2gmail.com")
            .build();

    @Test
    void should_generatePaymentMessage_when_receivePaymentNotification() {
        // given
        var expectedMessage = new SimpleMailMessage();
        final var expectedSubject = "Payment Notification";
        final var textTemplate = "You received a payment of %s. Thank you for using our service.";
        final var expectedText = String.format(textTemplate, PAYMENT_NOTIFICATION.amount());
        expectedMessage.setTo(PAYMENT_NOTIFICATION.emailTo());
        expectedMessage.setSubject(expectedSubject);
        expectedMessage.setText(expectedText);

        var paymentTemplateMessage = EmailTemplatesProperties.TemplateMessage.builder()
                .subject(expectedSubject)
                .text(expectedText)
                .build();

        given(emailTemplate.getPayment()).willReturn(paymentTemplateMessage);

        // when
        var result = mailMessageGenerator.getPaymentMessage(PAYMENT_NOTIFICATION);

        // then
        assertThat(result).isEqualTo(expectedMessage);
    }
}