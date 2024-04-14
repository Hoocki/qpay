package com.qpay.transactionhistorymanager.controller;

import com.qpay.libs.models.ReportInfo;
import com.qpay.transactionhistorymanager.service.ReportGeneratorService;
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

    private final ReportGeneratorService reportGeneratorService;

    @PostMapping(PathUtils.REPORT_PATH)
    public void generateReport(@Valid @RequestBody final ReportInfo reportInfo) {
        reportGeneratorService.generatePdfReport(reportInfo);
    }
}
