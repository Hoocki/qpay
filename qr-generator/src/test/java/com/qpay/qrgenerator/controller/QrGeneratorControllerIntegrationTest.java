package com.qpay.qrgenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.impl.QrCodeGeneratorZxing;
import com.qpay.qrgenerator.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    private QrCodeGeneratorZxing qrGeneratorService;

    @Test
    void should_returnQrCode() throws Exception {
        //given
        var qrCodeCreation = QrCodeCreation.builder()
                .walletId(1)
                .name("bob")
                .money(new BigDecimal(1234))
                .build();
        var qrCodeBytes = "Qr code data".getBytes();
        given(qrGeneratorService.generateQrCode(qrCodeCreation)).willReturn(qrCodeBytes);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post(PathUtils.QR_GENERATOR_BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(qrCodeCreation)))
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        //then
        assertThat(responseBody).isEqualTo(qrCodeBytes);
    }
}