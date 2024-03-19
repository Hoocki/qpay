package com.qpay.paymentmanager.client;

import com.qpay.libs.models.PaymentNotification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification")
public interface NotificationClient {

    String NOTIFICATION_PATH = "/api/v1/notifications";

    @PostMapping(value = NOTIFICATION_PATH)
    void sendNotification(PaymentNotification notificationCreation);

}
