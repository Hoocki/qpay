package com.qpay.transactionhistorymanager.controller;

import com.qpay.transactionhistorymanager.model.dto.TransactionModification;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.service.TransactionHistoryService;
import com.qpay.transactionhistorymanager.utility.PathUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = PathUtils.HISTORY_PATH)
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    @GetMapping("{userId}")
    public List<TransactionEntity> getPageOfTransactionsByUserId(
            @PathVariable
            final long userId,
            @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            final Pageable pageable
    ) {
        return transactionHistoryService.getPageOfTransactionsByUserId(userId, pageable);
    }

    @PostMapping
    public TransactionEntity saveTransaction(@Valid @RequestBody final TransactionModification transactionModification) {
        return transactionHistoryService.saveTransaction(transactionModification);
    }
}
