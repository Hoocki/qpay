package com.qpay.qrgenerator.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.exception.QrEncodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QrCodeGeneratorZxingTest {

    @InjectMocks
    private QrCodeGeneratorZxing qrGeneratorService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    void should_generateQrCode() throws Exception {
        //given
        var qrCodeCreation = QrCodeCreation.builder()
                .walletId(1)
                .name("bob")
                .money(new BigDecimal(1234))
                .build();

        given(objectMapper.writeValueAsString(qrCodeCreation)).willReturn("Qr code data");

        //when
        var image = qrGeneratorService.generateQrCode(qrCodeCreation);

        //then
        assertThat(image).isNotEmpty();
    }

    @Test
    void should_throwQrEncodeException_when_cantEncodeQr() throws Exception {
        //given
        var qrCodeCreation = QrCodeCreation.builder()
                .walletId(1)
                .name("bob")
                .money(new BigDecimal(1234))
                .build();

        given(objectMapper.writeValueAsString(qrCodeCreation)).willThrow(JsonProcessingException.class);

        //when
        var thrown = catchThrowable(() -> qrGeneratorService.generateQrCode(qrCodeCreation));

        //then
        assertThat(thrown).isInstanceOf(QrEncodeException.class);
    }}