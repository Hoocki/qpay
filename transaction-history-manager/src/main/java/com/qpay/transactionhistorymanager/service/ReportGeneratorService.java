package com.qpay.transactionhistorymanager.service;

import com.qpay.libs.models.ReportInfo;

public interface ReportGeneratorService {

    void generatePdfReport(final ReportInfo reportInfo);
}
