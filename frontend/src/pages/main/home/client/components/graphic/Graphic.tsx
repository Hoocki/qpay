import React, {useEffect, useState} from 'react';
import {Card, CardContent, Typography} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "./styles.css";
import "../balance/styles.css";
import {Pie} from "react-chartjs-2";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";
import {Content} from "../../../../../../common/constansts/content";
import {Transaction, TransactionType} from "../../../../../../types/transactions";
import {getTransactionForYearService} from "../../../../../../services/transaction";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import {CURRENCY_USD} from "../../../../../../common/constansts/currency";
import {OTHER_TRANSACTIONS, THRESHOLD_UNIQUE_TRANSACTIONS} from "../../../../../../common/constansts/transactions";
import {COLORS} from "../../../../../../common/constansts/colors";
import Finance from "../finance/Finance";

Chart.register(Tooltip, Legend, ArcElement);

const initialTransactions: Transaction[] = [];

const Graphic: React.FC = () => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [transactions, setTransactions] = useState<Transaction[]>(initialTransactions);
    const [income, setIncome] = useState<number>(0);
    const [expenses, setExpenses] = useState<number>(0);

    const updateTransactions = async () => {
        const transactionData = await getTransactionForYearService(loggedUser.id, loggedUser.userType, new Date().getFullYear(), new Date().getFullYear());
        const updatedTransactions = mergeTransactions(transactionData);
        setTransactions(updatedTransactions);
        const expenses = updatedTransactions.reduce((total, transaction) => total + transaction.amount, 0);
        setExpenses(expenses);
        const income = transactionData.filter(transaction => transaction.TransactionType === TransactionType.TOP_UP)
                                               .reduce((total, transaction) => total + transaction.amount, 0);
        setIncome(income);
    }

    const mergeTransactions = (transactions: Transaction[]): Transaction[] => {
        const mergedTransactions: { [nameTo: string]: Transaction } = {};
        const paymentTransactions = transactions.filter(transaction => transaction.TransactionType === TransactionType.PAYMENT);
        let numberUniqueTransactions = 0;
        let flag = true;

        paymentTransactions.forEach(transaction => {
            const key = transaction.nameTo;
            if (!mergedTransactions[key] && numberUniqueTransactions < THRESHOLD_UNIQUE_TRANSACTIONS) {
                mergedTransactions[key] = {...transaction};
                numberUniqueTransactions++;
            } else if (!mergedTransactions[key] && flag) {
                mergedTransactions[OTHER_TRANSACTIONS] = {...transaction, nameTo: OTHER_TRANSACTIONS};
                flag = false;
            } else if (!mergedTransactions[key]) {
                mergedTransactions[OTHER_TRANSACTIONS].amount += transaction.amount;
            } else {
                mergedTransactions[key].amount += transaction.amount;
            }
        });
        return Object.values(mergedTransactions);
    };

    const graphicData = {
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
    }

    useEffect(() => {
        updateTransactions().then();
    }, []);

    return (
        <Card className="card-background card-payment card-graphic">
            <CardContent className="card-content">
                <Typography
                    variant="h4"
                >
                    {Content.TRANSACTIONS}
                </Typography>
            </CardContent>
            <Finance income={income} expenses={expenses}/>
            {expenses > 0 ? <Pie options={{
                    responsive: true,
                    plugins: {
                        legend: {
                            display: true,
                            position: 'left',
                            align: "center",
                            labels: {
                                usePointStyle: true,
                                font: {
                                    size: 14,
                                    weight: "bold"
                                }
                            }
                        }
                    }
                }} data={graphicData} className="graphic"/>
                :
                <></>
            }
        </Card>
    );
};

export default Graphic;