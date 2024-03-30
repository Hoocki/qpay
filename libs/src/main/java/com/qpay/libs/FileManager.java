package com.qpay.libs;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileManager {

    public static void savePdf(final String path, final List<Paragraph> paragraphList) throws IOException {
        final PdfWriter pdfWriter = new PdfWriter(path);
        final PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        final Document document = new Document(pdfDocument);
        document.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
        document.setFontSize(10);

        for (final Paragraph paragraph : paragraphList) {
            document.add(paragraph);
        }

        document.close();
    }
}
