package com.qpay.paymentmanager.service.impl;

import com.qpay.libs.models.PaymentNotification;
import com.qpay.paymentmanager.client.TransactionHistoryClient;
import com.qpay.paymentmanager.event.PaymentNotificationProducer;
import com.qpay.paymentmanager.model.dto.PaymentTransaction;
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

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class PaymentServiceImpl implements PaymentService {

    private final WalletRepository walletRepository;

    private final WalletService walletService;

    private final TransactionHistoryClient transactionHistoryClient;

    private final PaymentNotificationProducer paymentNotificationProducer;

    public WalletEntity makePayment(final WalletPayment walletPayment) {
        isEnoughMoneyInWallet(walletPayment.amount());
        final var fromWallet = walletService.getWalletById(walletPayment.walletIdFrom());
        final var toWallet = walletService.getWalletById(walletPayment.walletIdTo());

        final var updatedFromWalletBalance = fromWallet.getBalance().subtract(walletPayment.amount());
        updateBalance(updatedFromWalletBalance, fromWallet);
        final var updatedToWalletBalance = toWallet.getBalance().add(walletPayment.amount());
        updateBalance(updatedToWalletBalance, toWallet);

        sendMessageToKafka(walletPayment);
        saveTransactionToHistory(
                fromWallet.getName(),
                toWallet.getName(),
                walletPayment
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

    private void saveTransactionToHistory(final String nameFrom, final String nameTo, final WalletPayment walletPayment) {
        final var transaction = PaymentTransaction.builder()
                .nameFrom(nameFrom)
                .nameTo(nameTo)
                .idFrom(walletPayment.walletIdFrom())
                .idTo(walletPayment.walletIdTo())
                .amount(walletPayment.amount())
                .build();
        transactionHistoryClient.saveTransactionToHistory(transaction);
    }

    private void sendMessageToKafka(final WalletPayment walletPayment) {
        final var paymentNotification  = PaymentNotification.builder()
                .amount(walletPayment.amount())
                .emailFrom(walletPayment.emailFrom())
                .emailTo(walletPayment.emailTo())
                .build();
        paymentNotificationProducer.publishPaymentNotification(paymentNotification );
    }

}
