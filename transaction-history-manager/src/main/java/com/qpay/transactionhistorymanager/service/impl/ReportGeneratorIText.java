package com.qpay.transactionhistorymanager.service.impl;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.qpay.libs.FileManager;
import com.qpay.libs.models.ReportInfo;
import com.qpay.libs.models.UserReportInfo;
import com.qpay.libs.models.UserType;
import com.qpay.transactionhistorymanager.client.UserClient;
import com.qpay.transactionhistorymanager.model.TransactionType;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.repository.TransactionRepository;
import com.qpay.transactionhistorymanager.service.ReportGeneratorService;
import com.qpay.transactionhistorymanager.service.exception.LogoFileNotFoundException;
import com.qpay.transactionhistorymanager.utility.ReportGeneratorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ReportGeneratorIText implements ReportGeneratorService {

    private final TransactionRepository transactionRepository;

    private final UserClient userClient;

    public void generatePdfReport(final ReportInfo reportInfo) {
        final UserReportInfo userReportInfo;
        if (reportInfo.userType() == UserType.CUSTOMER) {
            userReportInfo = userClient.getCustomerReportInfo(reportInfo.userId());
        } else {
            userReportInfo = userClient.getMerchantReportInfo(reportInfo.userId());
        }
        final var path = generatePath(reportInfo.userId());
        var totalReceived = BigDecimal.ZERO;
        var totalSent = BigDecimal.ZERO;
        final var transactionsTable = createTransactionsTable();
        final var transactionEntitiesInPeriod = getTransactionEntitiesInPeriod(reportInfo);
        for (int number = 0; number < transactionEntitiesInPeriod.size(); number++) {
            final var transactionEntity = transactionEntitiesInPeriod.get(number);
            final var amount = transactionEntity.getAmount();
            final var transactionType = transactionEntity.getTransactionType();
            transactionsTable.addCell(String.valueOf(number + 1));
            transactionsTable.addCell(transactionEntity.getCreatedAt().format(ReportGeneratorUtils.DATE_TIME_PATTERN));
            transactionsTable.addCell(transactionType.toString());
            transactionsTable.addCell(amount.toString());
            transactionsTable.addCell(transactionEntity.getNameTo());
            if (reportInfo.userType() == UserType.CUSTOMER) {
                if (transactionType == TransactionType.PAYMENT) {
                    totalSent = totalSent.add(amount);
                } else {
                    totalReceived = totalReceived.add(amount);
                }
            } else {
                totalReceived = totalReceived.add(amount);
            }
        }
        final List<Paragraph> paragraphList = List.of(
                createLogoParagraph(),
                createTitleParagraph(),
                createSubTitleParagraph(reportInfo),
                createClientNameParagraph(userReportInfo.name()),
                createTotalAmountParagraph("income", totalReceived),
                createTotalAmountParagraph("expenses", totalSent),
                new Paragraph().add(transactionsTable)
        );
        FileManager.savePdf(path, paragraphList);
    }

    private List<TransactionEntity> getTransactionEntitiesInPeriod(final ReportInfo reportInfo) {
        if (reportInfo.userType() == UserType.CUSTOMER) {
            return transactionRepository.findAllByIdFromAndCreatedAtBetween(
                            reportInfo.userId(),
                            reportInfo.periodStart(),
                            reportInfo.periodEnd()
                    );
        }
        return transactionRepository.findAllByIdToAndCreatedAtBetween(
                        reportInfo.userId(),
                        reportInfo.periodStart(),
                        reportInfo.periodEnd()
                );
    }

    private String generatePath(final long userId) {
        final var dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        final var currentDateTime = dateFormat.format(new Date());
        final var fileName = format(ReportGeneratorUtils.FILE_NAME, userId, currentDateTime);
        return ReportGeneratorUtils.REPORT_PATH + fileName;
    }

    private Table createTransactionsTable() {
        final var table = new Table(ReportGeneratorUtils.TABLE_COLUMN_WIDTHS);
        table.addCell("Number");
        table.addCell("Date");
        table.addCell("Transaction Type");
        table.addCell("Amount of money");
        table.addCell("Recipient/sender name");
        return table;
    }

    private Paragraph createLogoParagraph() {
        final var logoParagraph = new Paragraph();
        final ImageData imageData;
        try {
            imageData = ImageDataFactory.create(ReportGeneratorUtils.QPAY_LOGO_IMAGE_PATH);
        } catch (final MalformedURLException e) {
            throw new LogoFileNotFoundException(e.getMessage());
        }
        final var qpayLogoImage = new Image(imageData);
        qpayLogoImage.scale(ReportGeneratorUtils.LOGO_SCALE, ReportGeneratorUtils.LOGO_SCALE);
        logoParagraph.add(qpayLogoImage);
        return logoParagraph;
    }

    private Paragraph createTitleParagraph() {
        final var titleParagraph = new Paragraph();
        titleParagraph.setMarginTop(ReportGeneratorUtils.TITLE_MARGIN_TOP);
        titleParagraph.setTextAlignment(TextAlignment.RIGHT);
        final var titleText = new Text("Activity summary");
        titleText.setBold();
        titleText.setFontSize(ReportGeneratorUtils.TITLE_FONT_SIZE);
        titleParagraph.add(titleText);
        return titleParagraph;
    }

    private Paragraph createSubTitleParagraph(final ReportInfo reportInfo) {
        final var subTitleParagraph = new Paragraph();
        subTitleParagraph.setMarginTop(ReportGeneratorUtils.SUBTITLE_MARGIN_TOP);
        subTitleParagraph.setTextAlignment(TextAlignment.RIGHT);
        final var periodStart = reportInfo.periodStart().format(ReportGeneratorUtils.DATE_PATTERN);
        final var periodEnd = reportInfo.periodEnd().format(ReportGeneratorUtils.DATE_PATTERN);
        final var subTitleText = new Text(format(ReportGeneratorUtils.REPORT_PERIOD, periodStart, periodEnd));
        subTitleText.setFontSize(ReportGeneratorUtils.SUBTITLE_FONT_SIZE);
        subTitleParagraph.add(subTitleText);
        return subTitleParagraph;
    }

    private Paragraph createClientNameParagraph(final String name) {
        final var clientNameParagraph = new Paragraph();
        clientNameParagraph.setMarginTop(ReportGeneratorUtils.CLIENT_NAME_MARGIN_TOP);
        final var clientText = new Text("Client: ");
        final var nameText = new Text(name);
        nameText.setBold();
        clientNameParagraph.addAll(List.of(
                clientText,
                nameText
        ));
        return clientNameParagraph;
    }

    private Paragraph createTotalAmountParagraph(final String text, final BigDecimal total) {
        final var totalPaymentsReceivedParagraph = new Paragraph();
        final var totalPaymentsText = new Text("Total " + text + ": ");
        final var numberText = new Text(total.toString() + "$");
        numberText.setBold();
        totalPaymentsReceivedParagraph.addAll(List.of(
                totalPaymentsText,
                numberText
        ));
        return totalPaymentsReceivedParagraph;
    }
}
