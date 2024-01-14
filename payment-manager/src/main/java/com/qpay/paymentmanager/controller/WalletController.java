package com.qpay.paymentmanager.controller;

import com.qpay.libs.models.UserType;
import com.qpay.paymentmanager.model.dto.WalletCreation;
import com.qpay.paymentmanager.model.dto.WalletModification;
import com.qpay.paymentmanager.model.entity.WalletEntity;
import com.qpay.paymentmanager.service.WalletService;
import com.qpay.paymentmanager.utils.PathUtils;
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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.WALLET_PATH)
public class WalletController {

    private final WalletService walletService;

    @GetMapping("{id}")
    public WalletEntity getWalletById(@PathVariable final long id) {
        return walletService.getWalletById(id);
    }

    @GetMapping("users/{userId}")
    public WalletEntity getWalletByUser(@PathVariable final long userId, @RequestParam final UserType userType) {
        return walletService.getWalletByUser(userId, userType);
    }

    @PostMapping
    public WalletEntity addWallet(@Valid @RequestBody final WalletCreation walletCreation) {
        return walletService.addWallet(walletCreation);
    }

    @PutMapping("{id}")
    public WalletEntity updateWallet(@Valid @RequestBody final WalletModification walletModification,
                                     @PathVariable final long id) {
        return walletService.updateWallet(walletModification, id);
    }

    @DeleteMapping("{id}")
    public void deleteWallet(@PathVariable final long id) {
        walletService.deleteWallet(id);
    }

}
