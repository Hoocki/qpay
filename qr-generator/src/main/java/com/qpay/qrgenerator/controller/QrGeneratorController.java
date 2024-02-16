package com.qpay.qrgenerator.controller;

import com.qpay.qrgenerator.model.dto.QrCodeCreation;
import com.qpay.qrgenerator.service.QrGeneratorService;
import com.qpay.qrgenerator.utils.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.QR_GENERATOR_BASE_PATH)
public class QrGeneratorController {

    private final QrGeneratorService qrGeneratorService;

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQrCode(@Valid @RequestBody final QrCodeCreation qrCodeCreation) {
        return qrGeneratorService.generateQrCode(qrCodeCreation);
    }
}
