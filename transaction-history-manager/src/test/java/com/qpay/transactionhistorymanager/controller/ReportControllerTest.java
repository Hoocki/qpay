package com.qpay.transactionhistorymanager.controller;

import com.qpay.libs.models.ReportInfo;
import com.qpay.libs.models.UserType;
import com.qpay.transactionhistorymanager.service.ReportGeneratorService;
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
    private ReportGeneratorService reportGeneratorService;

    @Test
    void should_generateReport() {
        //given
        var pdfReportInfo = ReportInfo.builder()
                .userId(1)
                .userType(UserType.CUSTOMER)
                .periodStart(LocalDateTime.now())
                .periodEnd(LocalDateTime.now())
                .build();
        //when
        reportController.generateReport(pdfReportInfo);

        //then
        then(reportGeneratorService).should().generatePdfReport(pdfReportInfo);
    }
}