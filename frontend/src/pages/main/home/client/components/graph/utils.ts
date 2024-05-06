import {ITransactionGroup} from "../../../../../../types/transactions";
import {CURRENCY_USD} from "../../../../../../common/constansts/currency";
import {TRANSACTIONS_CHART_COLORS} from "../../../../../../common/constansts/colors";

export const getGraphData = (transactions: ITransactionGroup[]) => {
    return {
        labels:
            transactions.map(transaction => `${transaction.nameTo}: ${transaction.amount}${CURRENCY_USD} (${transaction.percentage}%)`),
        datasets: [
            {
                data: transactions.map((transaction) => transaction.amount),
                backgroundColor: TRANSACTIONS_CHART_COLORS,
                borderColor: "#ffffff",
                hoverOffSet: 4
            }
        ]
    };
}