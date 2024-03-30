package com.qpay.transactionhistorymanager.controller;

import com.qpay.libs.models.UserType;
import com.qpay.libs.models.PdfReportInfo;
import com.qpay.transactionhistorymanager.service.PdfReportGeneratorService;
import com.qpay.transactionhistorymanager.utility.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.HISTORY_PATH)
public class ReportController {

    private final PdfReportGeneratorService pdfReportGeneratorService;

    @PostMapping("report")
    public void generateReportAndSendToEmail(@Valid @RequestBody final PdfReportInfo pdfReportInfo) throws IOException {
        if (pdfReportInfo.userType() == UserType.CUSTOMER) {
            pdfReportGeneratorService.generateCustomerPdfReport(pdfReportInfo);
        } else {
            pdfReportGeneratorService.generateMerchantPdfReport(pdfReportInfo);
        }
    }
}
