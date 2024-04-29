package com.qpay.libs;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.qpay.libs.exception.PdfException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileManager {

    private static final int DEFAULT_FONT_SIZE = 10;

    public static void savePdf(final String path, final List<Paragraph> paragraphList) {
        try (
                final var pdfWriter = new PdfWriter(path);
                final var pdfDocument = new PdfDocument(pdfWriter);
                final var document = new Document(pdfDocument)
        ) {
            document.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
            document.setFontSize(DEFAULT_FONT_SIZE);
            paragraphList.forEach(document::add);
        } catch (final IOException e) {
            throw new PdfException(e.getMessage());
        }
    }
}
