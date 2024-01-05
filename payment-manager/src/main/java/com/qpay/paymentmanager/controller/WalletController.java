package com.qpay.paymentmanager.controller;

import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.utility.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.WALLET_PATH)
public class WalletController {

    private final WalletService walletService;

    @GetMapping("{id}")
    public WalletEntity getWalletById(@PathVariable final Long id) {
        return walletService.getWalletById(id);
    }

    @PostMapping
    public WalletEntity addWallet(@Valid @RequestBody final WalletModification walletModification) {
        return walletService.addWallet(walletModification);
    }

    @PutMapping("{id}")
    public WalletEntity updateWallet(@Valid @RequestBody final WalletModification walletModification,
                                     @PathVariable final Long id) {
        return walletService.updateWallet(walletModification, id);
    }

    @DeleteMapping("{id}")
    public void deleteWallet(@PathVariable final Long id) {
        walletService.deleteWallet(id);
    }

}
