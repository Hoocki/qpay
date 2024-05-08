package com.qpay.qrgenerator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.QrGeneratorService;
import com.qpay.qrgenerator.service.exception.QrEncodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class QrCodeGeneratorZxing implements QrGeneratorService {

    private static final int QR_CODE_SIZE = 240;

    private static final String QR_CODE_IMAGE_TYPE = "PNG";

    private final ObjectMapper objectMapper;

    public byte[] generateQrCode(final QrCodeCreation qrCodeCreation) {
        final var qrCodeWriter = new AztecWriter();
        final var outputStream = new ByteArrayOutputStream();
        try {
            final var json = objectMapper.writeValueAsString(qrCodeCreation);
            final var bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.AZTEC, QR_CODE_SIZE, QR_CODE_SIZE);
            MatrixToImageWriter.writeToStream(bitMatrix, QR_CODE_IMAGE_TYPE, outputStream, new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE));
        }  catch (final Exception err) {
            throw new QrEncodeException(err.getMessage());
        }
        return outputStream.toByteArray();
    }
}
