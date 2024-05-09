package com.qpay.notificationmanager.generator;

import com.qpay.libs.models.PaymentNotification;
import org.springframework.mail.SimpleMailMessage;

public interface MessageGenerator {

    SimpleMailMessage getPaymentMessageMerchant(PaymentNotification paymentNotification);

    SimpleMailMessage getPaymentMessageCustomer(PaymentNotification paymentNotification);

}
