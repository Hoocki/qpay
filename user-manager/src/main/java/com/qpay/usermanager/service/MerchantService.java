package com.qpay.usermanager.service;

import com.qpay.libs.models.MerchantReportInfo;
import com.qpay.usermanager.model.dto.merchant.MerchantCreation;
import com.qpay.usermanager.model.dto.merchant.MerchantModification;
import com.qpay.usermanager.model.entity.merchant.MerchantEntity;

import java.util.List;

public interface MerchantService {
    List<MerchantEntity> getMerchants();

    MerchantEntity getMerchantById(long id);

    MerchantEntity addMerchant(MerchantCreation merchantCreation);

    void deleteMerchant(long id);

    MerchantEntity updateMerchant(long id, MerchantModification merchantModification);

    MerchantReportInfo getMerchantReportInfo(final long id);
}
