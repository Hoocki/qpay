package com.qpay.paymentmanager.service;

import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;

public interface WalletService {

    WalletEntity getWalletById(Long id);

    WalletEntity addWallet(WalletModification walletModification);

    WalletEntity updateWallet(WalletModification walletModification, Long id);

    void deleteWallet(Long id);
}
