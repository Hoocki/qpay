package com.qpay.qrgenerator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QrGeneratorServiceImplTest {

    @Autowired
    private QrGeneratorServiceImpl qrGeneratorService;

    @Autowired
    ObjectMapper objectMapper;

    private static final int QR_CODE_SIZE = 240;

    @Test
    void should_GenerateQrCode() throws Exception {
        //given
        var qrCodeCreation = QrCodeCreation.builder().walletId(1).userName("bob").money(new BigDecimal(1234)).build();
        var json = objectMapper.writeValueAsString(qrCodeCreation);

        var qrCodeWriter = new QRCodeWriter();
        var outputStream = new ByteArrayOutputStream();
        var bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, new MatrixToImageConfig(MatrixToImageConfig.WHITE, MatrixToImageConfig.BLACK));

        //when
        var image = qrGeneratorService.generateQrCode(qrCodeCreation);

        //then
        assertThat(outputStream.toByteArray()).isEqualTo(image);
    }
}