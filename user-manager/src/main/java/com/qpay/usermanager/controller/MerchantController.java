package com.qpay.usermanager.controller;

import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;
import com.qpay.usermanager.service.MerchantService;
import com.qpay.usermanager.utility.PathsUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.qpay.usermanager.utility.PathsUtils.MERCHANTS_PATH;

@RestController
@RequestMapping(MERCHANTS_PATH)
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping
    public List<MerchantEntity> getMerchants() {
        return merchantService.getMerchants();
    }

    @GetMapping("{id}")
    public MerchantEntity getMerchantById(@PathVariable final long id) {
        return merchantService.getMerchantById(id);
    }

    @PostMapping(PathsUtils.SIGNUP_PATH)
    public MerchantEntity addMerchant(@Valid @RequestBody final MerchantCreation merchantCreation) {
        return merchantService.addMerchant(merchantCreation);
    }

    @DeleteMapping("{id}")
    public void deleteMerchant(@PathVariable final long id) {
        merchantService.deleteMerchant(id);
    }

    @PutMapping("{id}")
    public MerchantEntity updateMerchant(@PathVariable final long id, @Valid @RequestBody final MerchantCreation merchantCreation) {
        return merchantService.updateMerchant(id, merchantCreation);
    }
}
