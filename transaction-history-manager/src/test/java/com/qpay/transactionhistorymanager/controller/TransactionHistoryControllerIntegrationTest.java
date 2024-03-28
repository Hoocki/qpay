package com.qpay.transactionhistorymanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qpay.transactionhistorymanager.model.TransactionType;
import com.qpay.transactionhistorymanager.model.entity.TransactionEntity;
import com.qpay.transactionhistorymanager.service.impl.TransactionHistoryServiceImpl;
import com.qpay.transactionhistorymanager.utility.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = TransactionHistoryController.class)
@AutoConfigureDataMongo
class TransactionHistoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionHistoryServiceImpl transactionHistoryService;

    @Test
    void should_getTransactions_when_pageProvided() throws Exception {
        //given
        var transaction1 = TransactionEntity.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .transactionType(TransactionType.TRANSFER)
                .build();

        var transaction2 = TransactionEntity.builder()
                .nameFrom("c")
                .idFrom(3)
                .nameTo("a")
                .idTo(1)
                .amount(new BigDecimal(2))
                .transactionType(TransactionType.TRANSFER)
                .build();

        var list = List.of(transaction1, transaction2);
        var pageable = PageRequest.of(0, 2, Sort.by("createdAt").descending());
        given(transactionHistoryService.getPageOfTransactionsByUserId(1, pageable)).willReturn(list);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(PathUtils.HISTORY_PATH + "/{id}", 1)
                        .param("page", "0")
                        .param("size", "2")
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(list);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_getTransactionsOnDefaultPage_when_pageIsNotProvided() throws Exception {
        //given
        var transaction1 = TransactionEntity.builder()
                .nameFrom("a")
                .idFrom(1)
                .nameTo("b")
                .idTo(2)
                .amount(new BigDecimal(1))
                .transactionType(TransactionType.TRANSFER)
                .build();

        var transaction2 = TransactionEntity.builder()
                .nameFrom("c")
                .idFrom(3)
                .nameTo("a")
                .idTo(1)
                .amount(new BigDecimal(2))
                .transactionType(TransactionType.TRANSFER)
                .build();

        var list = List.of(transaction1, transaction2);
        var pageable = PageRequest.of(0, 25, Sort.by("createdAt").descending());
        given(transactionHistoryService.getPageOfTransactionsByUserId(1, pageable)).willReturn(list);

        //when
        var responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .get(PathUtils.HISTORY_PATH + "/{id}", 1)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        var expectedResponseBody = objectMapper.writeValueAsString(list);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }
}