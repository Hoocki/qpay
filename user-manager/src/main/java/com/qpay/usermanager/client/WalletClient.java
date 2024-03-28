package com.qpay.usermanager.client;

import com.qpay.usermanager.model.dto.wallet.WalletCreation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment")
public interface WalletClient {

    String WALLET_PATH = "/api/v1/wallets";

    @PostMapping(value = WALLET_PATH)
    void createWallet(WalletCreation walletCreation);
}
