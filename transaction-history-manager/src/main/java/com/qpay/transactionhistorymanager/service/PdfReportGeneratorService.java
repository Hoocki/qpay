package com.qpay.transactionhistorymanager.service;

import com.qpay.libs.models.PdfReportInfo;
import java.io.IOException;

public interface PdfReportGeneratorService {

    void generateCustomerPdfReport(final PdfReportInfo pdfReportInfo) throws IOException;

    void generateMerchantPdfReport(final PdfReportInfo pdfReportInfo) throws IOException;
}
