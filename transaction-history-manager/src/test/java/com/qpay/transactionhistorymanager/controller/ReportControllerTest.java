package com.qpay.transactionhistorymanager.controller;

import com.qpay.libs.models.PdfReportInfo;
import com.qpay.libs.models.UserType;
import com.qpay.transactionhistorymanager.service.PdfReportGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private PdfReportGeneratorService pdfReportGeneratorService;

    @Test
    void should_generateReportAndSendToEmail() throws IOException {
        //given
        var pdfReportInfo = PdfReportInfo.builder()
                .userId(1)
                .userType(UserType.CUSTOMER)
                .periodStart(LocalDateTime.now())
                .periodEnd(LocalDateTime.now())
                .build();
        //when
        reportController.generateReportAndSendToEmail(pdfReportInfo);

        //then
        then(pdfReportGeneratorService).should().generateCustomerPdfReport(pdfReportInfo);
    }
}