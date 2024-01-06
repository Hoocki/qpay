package com.qpay.paymentmanager.service.impl;

import com.qpay.paymentmanager.mapper.WalletMapper;
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

    public WalletEntity getWalletById(long id) {
        return walletRepository.findById(id).orElseThrow(NoWalletFoundException::new);
    }

    public WalletEntity addWallet(WalletModification walletModification) {
        final var walletEntity = walletMapper.map(walletModification);

        return walletRepository.save(walletEntity);
    }

    public WalletEntity updateWallet(WalletModification walletModification, long id) {
        var walletEntity = getWalletById(id);
        walletEntity.setName(walletModification.name());
        walletEntity.setBalance(walletModification.balance());
        walletEntity.setUserId(walletModification.userId());

        return walletRepository.save(walletEntity);
    }

    public void deleteWallet(long id) {
        walletRepository.deleteById(id);
    }

}
