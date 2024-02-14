package com.qpay.qrgenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.impl.QrGeneratorServiceImpl;
import com.qpay.qrgenerator.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = QrGeneratorController.class)
class QrGeneratorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private QrGeneratorServiceImpl qrGeneratorService;

    private static final int QR_CODE_SIZE = 240;

    @Test
    void should_returnQrCode() throws Exception {
        //given
        var qrCodeCreation = QrCodeCreation.builder().walletId(1).userName("bob").money(new BigDecimal(1234)).build();
        var json = objectMapper.writeValueAsString(qrCodeCreation);

        var qrCodeWriter = new QRCodeWriter();
        var outputStream = new ByteArrayOutputStream();
        var bitMatrix = qrCodeWriter.encode(json, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, new MatrixToImageConfig(MatrixToImageConfig.WHITE, MatrixToImageConfig.BLACK));

        given(qrGeneratorService.generateQrCode(qrCodeCreation)).willReturn(outputStream.toByteArray());

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.QR_GENERATOR_PATH + "/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(qrCodeCreation)))
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        //then
        var expectedResponseBody = outputStream.toByteArray();
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }
}