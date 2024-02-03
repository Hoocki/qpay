package com.qpay.paymentmanager.service.impl;

import com.qpay.paymentmanager.client.TransactionHistoryClient;
import com.qpay.paymentmanager.model.dto.PaymentTransaction;
import com.qpay.paymentmanager.client.NotificationClient;
import com.qpay.paymentmanager.model.dto.WalletPayment;
import com.qpay.paymentmanager.model.dto.WalletTopUp;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.repository.WalletRepository;
import com.qpay.paymentmanager.service.PaymentService;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.service.exception.NotEnoughMoneyWalletException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class PaymentServiceImpl implements PaymentService {

    private final WalletRepository walletRepository;

    private final WalletService walletService;

    private final ExecutorService executorService;

    private final NotificationClient notificationClient;

    private final TransactionHistoryClient transactionHistoryClient;

    public WalletEntity makePayment(final WalletPayment walletPayment) {
        isEnoughMoneyInWallet(walletPayment.amount());
        final var fromWallet = walletService.getWalletById(walletPayment.walletIdFrom());
        final var toWallet = walletService.getWalletById(walletPayment.walletIdTo());

        final var updatedFromWalletBalance = fromWallet.getBalance().subtract(walletPayment.amount());
        updateBalance(updatedFromWalletBalance, fromWallet);
        final var updatedToWalletBalance = toWallet.getBalance().add(walletPayment.amount());
        updateBalance(updatedToWalletBalance, toWallet);

        executorService.execute(() -> notificationClient.sendNotification(walletPayment));
        saveTransactionToHistory(
                fromWallet.getName(),
                toWallet.getName(),
                walletPayment.walletIdFrom(),
                walletPayment.walletIdTo(),
                walletPayment.amount()
                );

        return fromWallet;
    }

    public WalletEntity topUp(final WalletTopUp walletTopUp, final long id) {
        isEnoughMoneyInWallet(walletTopUp.amount());
        final var walletEntity = walletService.getWalletById(id);
        final var balanceWallet = walletEntity.getBalance();
        final var updatedBalanceWallet = balanceWallet.add(walletTopUp.amount());
        return updateBalance(updatedBalanceWallet, walletEntity);
    }

    private WalletEntity updateBalance(final BigDecimal balance, final WalletEntity wallet) {
        isEnoughMoneyInWallet(balance);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }

    private void isEnoughMoneyInWallet(final BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyWalletException();
        }
    }

    private void saveTransactionToHistory(final String nameFrom, final String nameTo, final long idFrom, final long idTo, final BigDecimal amount) {
        final var transaction = PaymentTransaction.builder()
                .nameFrom(nameFrom)
                .nameTo(nameTo)
                .idFrom(idFrom)
                .idTo(idTo)
                .amount(amount)
                .build();
        transactionHistoryClient.saveTransactionToHistory(transaction);
    }

}
