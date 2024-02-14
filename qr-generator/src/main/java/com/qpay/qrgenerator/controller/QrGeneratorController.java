package com.qpay.qrgenerator.controller;

import com.google.zxing.WriterException;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.QrGeneratorService;
import com.qpay.qrgenerator.utils.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.QR_GENERATOR_PATH)
public class QrGeneratorController {

    private final QrGeneratorService qrGeneratorService;

    @PostMapping(
            value = "/generate",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] generateQrCode(@Valid @RequestBody final QrCodeCreation qrCodeCreation) throws IOException, WriterException {
        return qrGeneratorService.generateQrCode(qrCodeCreation);
    }
}
