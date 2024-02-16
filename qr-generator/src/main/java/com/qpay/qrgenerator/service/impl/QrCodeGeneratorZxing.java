package com.qpay.qrgenerator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.QrGeneratorService;
import com.qpay.qrgenerator.service.exception.CantMapQrCodeCreationToJsonException;
import com.qpay.qrgenerator.service.exception.QrCodeToImageWriteException;
import com.qpay.qrgenerator.service.exception.QrEncodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QrCodeGeneratorZxing implements QrGeneratorService {

    private static final int QR_CODE_SIZE = 240;

    private static final String QR_CODE_IMAGE_TYPE = "PNG";

    private final ObjectMapper objectMapper;

    public byte[] generateQrCode(final QrCodeCreation qrCodeCreation) {
        final String json = mapQrCodeCreationToJson(qrCodeCreation);
        final BitMatrix bitMatrix = generateQrCode(json);
        final var outputStream = writeQrCodeToImage(bitMatrix);
        return outputStream.toByteArray();
    }

    private String mapQrCodeCreationToJson(final QrCodeCreation qrCodeCreation) {
        try {
            return objectMapper.writeValueAsString(qrCodeCreation);
        } catch (final IOException e) {
            throw new CantMapQrCodeCreationToJsonException(e.getMessage());
        }
    }

    private BitMatrix generateQrCode(final String json) {
        final var qrCodeWriter = new QRCodeWriter();
        try {
            return qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        } catch (final WriterException e) {
            throw new QrEncodeException(e.getMessage());
        }
    }

    private ByteArrayOutputStream writeQrCodeToImage(final BitMatrix bitMatrix) {
        final var outputStream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(bitMatrix, QR_CODE_IMAGE_TYPE, outputStream, new MatrixToImageConfig(MatrixToImageConfig.WHITE, MatrixToImageConfig.BLACK));
        } catch (final IOException e) {
            throw new QrCodeToImageWriteException(e.getMessage());
        }
        return outputStream;
    }
}
