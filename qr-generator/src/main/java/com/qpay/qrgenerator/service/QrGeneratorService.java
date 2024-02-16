package com.qpay.qrgenerator.service;

import com.qpay.qrgenerator.model.dto.QrCodeCreation;


public interface QrGeneratorService {

    byte[] generateQrCode(QrCodeCreation qrCodeCreation);
}
