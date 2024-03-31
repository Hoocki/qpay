package com.qpay.transactionhistorymanager.service.impl;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.qpay.libs.FileManager;
import com.qpay.libs.models.CustomerReportInfo;
import com.qpay.libs.models.MerchantReportInfo;
import com.qpay.transactionhistorymanager.client.UserClient;
import com.qpay.transactionhistorymanager.model.TransactionType;
import com.qpay.libs.models.PdfReportInfo;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.repository.TransactionRepository;
import com.qpay.transactionhistorymanager.service.PdfReportGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfReportGeneratorITextPdf implements PdfReportGeneratorService {

    private final TransactionRepository transactionRepository;

    private final UserClient userClient;

    private static final String QPAY_LOGO_IMAGE_PATH = "transaction-history-manager/src/main/resources/image/logo.png";

    private static final String PDF_PATH = "data/reports/";

    public void generateCustomerPdfReport(final PdfReportInfo pdfReportInfo) throws IOException {
        final CustomerReportInfo customerReportInfo = userClient.getCustomerReportInfo(pdfReportInfo.userId());

        final String path = generatePath(pdfReportInfo.userId());
        BigDecimal totalReceived = BigDecimal.valueOf(0);
        BigDecimal totalSent = BigDecimal.valueOf(0);
        final Table transactionsTable = createTransactionsTable();
        final List<TransactionEntity> transactionEntitiesInPeriod = transactionRepository
                .findAllByIdFromAndCreatedAtBetween(
                        pdfReportInfo.userId(),
                        pdfReportInfo.periodStart(),
                        pdfReportInfo.periodEnd()
                );
        for (int number = 0; number < transactionEntitiesInPeriod.size(); number++) {
            final TransactionEntity transactionEntity = transactionEntitiesInPeriod.get(number);
            final BigDecimal amount = transactionEntity.getAmount();
            final TransactionType transactionType = transactionEntity.getTransactionType();

            transactionsTable.addCell(String.valueOf(number + 1));
            transactionsTable.addCell(transactionEntity.getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            ));
            transactionsTable.addCell(transactionType.toString());
            transactionsTable.addCell(amount.toString());
            transactionsTable.addCell(transactionEntity.getNameTo());

            if (transactionType == TransactionType.PAYMENT) {
                totalSent = totalSent.add(amount);
            } else if (transactionType == TransactionType.TOP_UP) {
                totalReceived = totalReceived.add(amount);
            }
        }

        final List<Paragraph> paragraphList = List.of(
                createLogoParagraph(),
                createTitleParagraph(),
                createSubTitleParagraph(pdfReportInfo),
                createClientNameParagraph(customerReportInfo.name()),
                createTotalPaymentsReceivedParagraph(totalReceived),
                createTotalPaymentsSentParagraph(totalSent),
                new Paragraph().add(transactionsTable)
        );
        FileManager.savePdf(path, paragraphList);
    }

    public void generateMerchantPdfReport(final PdfReportInfo pdfReportInfo) throws IOException {
        final MerchantReportInfo merchantReportInfo = userClient.getMerchantReportInfo(pdfReportInfo.userId());

        final String path = generatePath(pdfReportInfo.userId());
        BigDecimal totalReceived = BigDecimal.valueOf(0);
        BigDecimal totalSent = BigDecimal.valueOf(0);
        final Table transactionsTable = createTransactionsTable();
        final List<TransactionEntity> transactionEntitiesInPeriod = transactionRepository
                .findAllByIdToAndCreatedAtBetween(
                        pdfReportInfo.userId(),
                        pdfReportInfo.periodStart(),
                        pdfReportInfo.periodEnd()
                );
        for (int number = 0; number < transactionEntitiesInPeriod.size(); number++) {
            final TransactionEntity transactionEntity = transactionEntitiesInPeriod.get(number);
            final BigDecimal amount = transactionEntity.getAmount();
            final TransactionType transactionType = transactionEntity.getTransactionType();

            transactionsTable.addCell(String.valueOf(number + 1));
            transactionsTable.addCell(transactionEntity.getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    ));
            transactionsTable.addCell(transactionType.toString());
            transactionsTable.addCell(amount.toString());
            transactionsTable.addCell(transactionEntity.getNameTo());

            totalReceived = totalReceived.add(amount);
        }

        final List<Paragraph> paragraphList = List.of(
                createLogoParagraph(),
                createTitleParagraph(),
                createSubTitleParagraph(pdfReportInfo),
                createClientNameParagraph(merchantReportInfo.name()),
                createTotalPaymentsReceivedParagraph(totalReceived),
                createTotalPaymentsSentParagraph(totalSent),
                new Paragraph().add(transactionsTable)
        );
        FileManager.savePdf(path, paragraphList);
    }

    private String generatePath(final long userId) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        final String currentDateTime = dateFormat.format(new Date());
        return PDF_PATH + userId + "_" + currentDateTime + ".pdf";
    }

    private Table createTransactionsTable() {
        final float [] pointColumnWidths = {50F, 150F, 100F, 150F, 250F};
        final Table table = new Table(pointColumnWidths);
        table.addCell("Number");
        table.addCell("Date");
        table.addCell("Transaction Type");
        table.addCell("Amount of money");
        table.addCell("Recipient/sender name");
        return table;
    }

    private Paragraph createLogoParagraph() throws MalformedURLException {
        final Paragraph logoParagraph = new Paragraph();
        final ImageData imageData = ImageDataFactory.create(QPAY_LOGO_IMAGE_PATH);
        final Image qpayLogoImage = new Image(imageData);
        qpayLogoImage.scale(0.4F, 0.4F);
        logoParagraph.add(qpayLogoImage);
        return logoParagraph;
    }

    private Paragraph createTitleParagraph() {
        final Paragraph titleParagraph = new Paragraph();
        titleParagraph.setMarginTop(-75);
        titleParagraph.setTextAlignment(TextAlignment.RIGHT);
        final Text titleText = new Text("Activity summary");
        titleText.setBold();
        titleText.setFontSize(32);
        titleParagraph.add(titleText);

        return titleParagraph;
    }

    private Paragraph createSubTitleParagraph(final PdfReportInfo pdfReportInfo) {
        final Paragraph subTitleParagraph = new Paragraph();
        subTitleParagraph.setMarginTop(-10);
        subTitleParagraph.setTextAlignment(TextAlignment.RIGHT);
        final String periodStart = pdfReportInfo.periodStart().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        final String periodEnd = pdfReportInfo.periodEnd().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        final Text subTitleText = new Text("For period from " + periodStart + " to " + periodEnd);
        subTitleText.setFontSize(14);

        subTitleParagraph.add(subTitleText);

        return subTitleParagraph;
    }

    private Paragraph createClientNameParagraph(final String name) {
        final Paragraph clientNameParagraph = new Paragraph();
        clientNameParagraph.setMarginTop(15);
        final Text clientText = new Text("Client: ");
        final Text nameText = new Text(name);
        nameText.setBold();
        clientNameParagraph.addAll(List.of(
                clientText,
                nameText
        ));
        return clientNameParagraph;
    }

    private Paragraph createTotalPaymentsReceivedParagraph(final BigDecimal total) {
        final Paragraph totalPaymentsReceivedParagraph = new Paragraph();
        final Text totalPaymentsText = new Text("Total payments received: ");
        final Text numberText = new Text(total.toString() + "$");
        numberText.setBold();
        totalPaymentsReceivedParagraph.addAll(List.of(
                totalPaymentsText,
                numberText
        ));
        return totalPaymentsReceivedParagraph;
    }

    private Paragraph createTotalPaymentsSentParagraph(final BigDecimal total) {
        final Paragraph totalPaymentsSentParagraph = new Paragraph();
        final Text totalPaymentsText = new Text("Total payments sent: ");
        final Text numberText = new Text(total.toString() + "$");
        numberText.setBold();
        totalPaymentsSentParagraph.addAll(List.of(
                totalPaymentsText,
                numberText
        ));
        return totalPaymentsSentParagraph;
    }

}
