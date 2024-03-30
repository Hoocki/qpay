package com.qpay.transactionhistorymanager.client;

import com.qpay.libs.models.CustomerReportInfo;
import com.qpay.libs.models.MerchantReportInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users")
public interface UserClient {

    String CUSTOMER_PATH = "/api/v1/customers";

    String MERCHANT_PATH = "/api/v1/merchants";

    String REPORT_PATH = "/report";

    String USER_ID = "/{userId}";

    @GetMapping(value = CUSTOMER_PATH + REPORT_PATH + USER_ID)
    CustomerReportInfo getCustomerReportInfo(@PathVariable long userId);

    @GetMapping(value = MERCHANT_PATH + REPORT_PATH + USER_ID)
    MerchantReportInfo getMerchantReportInfo(@PathVariable long userId);
}
