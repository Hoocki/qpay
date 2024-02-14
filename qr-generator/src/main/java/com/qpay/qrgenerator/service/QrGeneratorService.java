package com.qpay.qrgenerator.service;

import com.google.zxing.WriterException;
import com.qpay.qrgenerator.model.dto.QrCodeCreation;

import java.io.IOException;

public interface QrGeneratorService {

    byte[] generateQrCode(QrCodeCreation qrCodeCreation) throws IOException, WriterException;
}
