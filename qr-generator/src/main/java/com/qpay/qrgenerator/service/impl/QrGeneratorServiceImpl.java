package com.qpay.qrgenerator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.QrGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QrGeneratorServiceImpl implements QrGeneratorService {

    private static final int QR_CODE_SIZE = 240;

    private final ObjectMapper objectMapper;

    public byte[] generateQrCode(final QrCodeCreation qrCodeCreation) throws IOException, WriterException {
        final var json = objectMapper.writeValueAsString(qrCodeCreation);

        final var qrCodeWriter = new QRCodeWriter();
        final var outputStream = new ByteArrayOutputStream();
        final var bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, new MatrixToImageConfig(MatrixToImageConfig.WHITE, MatrixToImageConfig.BLACK));

        return outputStream.toByteArray();
    }
}
