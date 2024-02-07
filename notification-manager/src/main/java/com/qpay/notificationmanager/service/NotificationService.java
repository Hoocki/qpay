package com.qpay.notificationmanager.service;


import com.qpay.libs.models.PaymentNotification;

public interface NotificationService {

    void sendMessage(PaymentNotification paymentNotification);
}
