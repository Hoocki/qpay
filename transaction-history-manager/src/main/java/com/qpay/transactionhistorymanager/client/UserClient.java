package com.qpay.transactionhistorymanager.client;

import com.qpay.libs.models.UserReportInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users")
public interface UserClient {

    String CUSTOMER_PATH = "/api/v1/customers";

    String MERCHANT_PATH = "/api/v1/merchants";

    String USER_ID = "/{userId}";

    @GetMapping(value = CUSTOMER_PATH + USER_ID)
    UserReportInfo getCustomerReportInfo(@PathVariable long userId);

    @GetMapping(value = MERCHANT_PATH  + USER_ID)
    UserReportInfo getMerchantReportInfo(@PathVariable long userId);
}
