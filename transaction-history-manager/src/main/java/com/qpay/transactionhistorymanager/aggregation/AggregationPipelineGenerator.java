package com.qpay.transactionhistorymanager.aggregation;

import com.mongodb.BasicDBObject;
import com.qpay.libs.models.TransactionType;
import com.qpay.transactionhistorymanager.model.dto.Outcome;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AggregationPipelineGenerator {

    private final MongoTemplate mongoTemplate;

    public Outcome generatePipeline(final long walletId, final LocalDateTime startDate, final LocalDateTime endDate) {
        final var filterTransactions = Aggregation.match(
                Criteria.where("transactionType").is(TransactionType.PAYMENT)
                        .and("idFrom").is(walletId)
                        .and("createdAt").gte(startDate).lte(endDate)
        );

        final var groupByIdTo = Aggregation.group("idTo")
                .sum("amount").as("amount")
                .first("nameTo").as("destination");

        final var sortDesc = Aggregation.sort(Sort.by(Sort.Direction.DESC, "amount"));

        final var groupTransactionsAndCalculateTotal = Aggregation.group()
                .sum("amount").as("total")
                .push(new BasicDBObject()
                        .append("idTo", "$_id")
                        .append("amount", "$amount")
                        .append("destination", "$destination"))
                .as("transactionsGroup");

        final var limitTransactionsGroup = Aggregation.project()
                .andExclude("_id")
                .andInclude("total")
                .andExpression("transactionsGroup").slice(3).as("transactionsGroup");

        final var unwindTransactionsGroup = Aggregation.unwind("transactionsGroup");

        final var addShareField = Aggregation.addFields()
                .addFieldWithValue("transactionsGroup.share",
                        AggregationExpression.from(
                                MongoExpression.create("{ $round: { $multiply: [{ $divide: ['$transactionsGroup.amount', '$total'] }, 100] } }")
                        )
                ).build();

        final var finalGroupOperation = Aggregation.group()
                .first("total").as("total")
                .push("transactionsGroup").as("transactionsGroup");

        final var finalProjection = Aggregation.project()
                .andExclude("_id");

        final var aggregation = Aggregation.newAggregation(
                filterTransactions,
                groupByIdTo,
                sortDesc,
                groupTransactionsAndCalculateTotal,
                limitTransactionsGroup,
                unwindTransactionsGroup,
                addShareField,
                finalGroupOperation,
                finalProjection
        );

        final AggregationResults<Outcome> output
                = mongoTemplate.aggregate(aggregation, "transactions", Outcome.class);
        final var expenses = output.getUniqueMappedResult();
        if (expenses == null) {
            return Outcome.builder()
                    .total(BigDecimal.ZERO)
                    .transactionsGroup(List.of())
                    .build();
        }
        return expenses;
    }
}