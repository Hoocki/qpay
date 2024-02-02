package com.qpay.notificationmanager.controller;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.notificationmanager.service.NotificationService;
import com.qpay.notificationmanager.utils.PathUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.NOTIFICATION_PATH)
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendMessageToEmail(@RequestBody final PaymentNotification paymentNotification) {
        notificationService.sendMessage(paymentNotification);
    }
}
