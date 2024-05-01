package com.qpay.notificationmanager.service;


import com.qpay.libs.models.PaymentNotification;
import com.qpay.libs.models.ReportNotification;

public interface NotificationService {

    void sendMessage(PaymentNotification paymentNotification);

    void sendMessage(ReportNotification reportNotification);
}
