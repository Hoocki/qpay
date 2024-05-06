import {ITransaction} from "../../../../../../types/transactions";
import {CURRENCY_USD} from "../../../../../../common/constansts/currency";
import {COLORS} from "../../../../../../common/constansts/colors";

export const getGraphData = (transactions: ITransaction[], expenses: number) => {
    return {
        labels:
            transactions.map(transaction => {
                const percentage = Math.round((transaction.amount / expenses) * 100);
                return `${transaction.nameTo}: ${transaction.amount}${CURRENCY_USD} (${percentage}%)`;
            }),
        datasets: [
            {
                data: transactions.map((transaction) => transaction.amount),
                backgroundColor: COLORS,
                borderColor: "#ffffff",
                hoverOffSet: 4
            }
        ]
    };
}