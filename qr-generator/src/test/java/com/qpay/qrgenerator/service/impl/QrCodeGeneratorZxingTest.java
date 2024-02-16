package com.qpay.qrgenerator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class QrCodeGeneratorZxingTest {

    private final QrCodeGeneratorZxing qrGeneratorService = new QrCodeGeneratorZxing(new ObjectMapper());

    @Test
    void should_generateQrCode() {
        //given
        var qrCodeCreation = QrCodeCreation.builder()
                .walletId(1)
                .name("bob")
                .money(new BigDecimal(1234))
                .build();

        //when
        var image = qrGeneratorService.generateQrCode(qrCodeCreation);

        //then
        assertThat(image).isNotEmpty();
    }
}