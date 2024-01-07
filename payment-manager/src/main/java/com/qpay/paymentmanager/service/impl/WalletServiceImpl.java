package com.qpay.paymentmanager.service.impl;

import com.qpay.paymentmanager.mapper.WalletMapper;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.repository.WalletRepository;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.service.exception.NoWalletFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final WalletMapper walletMapper;

    public WalletEntity getWalletById(final long id) {
        return walletRepository.findById(id).orElseThrow(NoWalletFoundException::new);
    }

    public WalletEntity addWallet(final WalletCreation walletCreation) {
        final var walletEntity = walletMapper.map(walletCreation);
        return walletRepository.save(walletEntity);
    }

    public WalletEntity updateWallet(final WalletModification walletModification, final long id) {
        final var walletEntity = getWalletById(id);
        walletEntity.setName(walletModification.name());
        return walletRepository.save(walletEntity);
    }

    public void deleteWallet(final long id) {
        walletRepository.deleteById(id);
    }

}
