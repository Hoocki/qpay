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
public final class FileManager {

    private static final int DEFAULT_FONT_SIZE = 10;

    public static void savePdf(final String path, final List<Paragraph> paragraphList) throws IOException {
        final PdfWriter pdfWriter;
        final PdfDocument pdfDocument;
        Document document = null;
        try {
            pdfWriter = new PdfWriter(path);
            pdfDocument = new PdfDocument(pdfWriter);
            document = new Document(pdfDocument);
            document.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
        } catch (final IOException e) {
            if (document != null) {
                document.close();
            }
            throw new IOException(e);
        }
        document.setFontSize(DEFAULT_FONT_SIZE);
        paragraphList.forEach(document::add);
        document.close();
    }
}
