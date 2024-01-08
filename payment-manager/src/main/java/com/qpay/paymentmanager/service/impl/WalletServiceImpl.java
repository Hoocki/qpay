package com.qpay.paymentmanager.service.impl;

import com.qpay.paymentmanager.mapper.WalletMapper;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.repository.WalletRepository;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.service.exception.NoWalletFoundException;
import com.qpay.paymentmanager.service.exception.NotEnoughMoneyWalletException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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

    @Transactional
    public WalletEntity paymentWallet(final WalletPayment walletPayment) {
        isEnoughMoneyInWallet(walletPayment.amount());
        final var fromWallet = getWalletById(walletPayment.walletIdFrom());
        final var toWallet = getWalletById(walletPayment.walletIdTo());
        final var fromWalletBalance = fromWallet.getBalance();
        final var toWalletBalance = toWallet.getBalance();

        final var currentFromWalletBalance = fromWalletBalance.subtract(walletPayment.amount());
        isEnoughMoneyInWallet(currentFromWalletBalance);
        fromWallet.setBalance(currentFromWalletBalance);
        walletRepository.save(fromWallet);

        final var currentToWalletBalance = toWalletBalance.add(walletPayment.amount());
        isEnoughMoneyInWallet(currentToWalletBalance);
        toWallet.setBalance(currentToWalletBalance);
        walletRepository.save(toWallet);

        return fromWallet;
    }

    public WalletEntity topUpWallet(final WalletTopUp walletTopUp, final long id) {
        isEnoughMoneyInWallet(walletTopUp.amount());
        final var walletEntity = getWalletById(id);
        final var currentBalanceWallet = walletEntity.getBalance();
        final var newBalanceWallet = currentBalanceWallet.add(walletTopUp.amount());
        isEnoughMoneyInWallet(newBalanceWallet);
        walletEntity.setBalance(newBalanceWallet);
        return walletRepository.save(walletEntity);
    }

    private void isEnoughMoneyInWallet(final BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyWalletException();
        }
    }

}
