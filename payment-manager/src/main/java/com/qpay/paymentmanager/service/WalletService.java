package com.qpay.paymentmanager.service;

import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;

public interface WalletService {

    WalletEntity getWalletById(long id);

    WalletEntity addWallet(WalletModification walletModification);

    WalletEntity updateWallet(WalletModification walletModification, long id);

    void deleteWallet(long id);
}
