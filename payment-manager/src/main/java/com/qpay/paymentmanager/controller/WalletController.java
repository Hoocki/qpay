package com.qpay.paymentmanager.controller;

import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.utility.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
