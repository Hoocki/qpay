package com.qpay.notificationmanager.generator;

import com.qpay.libs.models.PaymentNotification;
import org.springframework.mail.SimpleMailMessage;

public interface MessageGenerator {

    SimpleMailMessage getPaymentMessage(PaymentNotification paymentNotification);

}
