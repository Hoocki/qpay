import React, {useEffect, useState} from 'react';
import {Card, CardContent, Grid, Typography} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "./styles.css";
import "../balance/styles.css";
import {Pie} from "react-chartjs-2";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";
import {Content} from "../../../../../../common/constansts/content";
import {ITransaction, TransactionType} from "../../../../../../types/transactions";
import {getTransactionsInRange} from "../../../../../../services/transaction";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import FinancialSummary from "../financialSummary/FinancialSummary";
import {getGraphData} from "./graphSettings";

Chart.register(Tooltip, Legend, ArcElement);

const Graph: React.FC = () => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [transactions, setTransactions] = useState<ITransaction[]>([]);
    const [income, setIncome] = useState<number>(0);
    const [expenses, setExpenses] = useState<number>(0);

    const transformTransactionsAndCalculateFinancials = async () => {
        const transactionsData = await getTransactionsInRange(loggedUser.id, loggedUser.userType, new Date(), new Date());
        const paymentTransactions = transactionsData.filter(transaction => transaction.TransactionType === TransactionType.PAYMENT);
        setTransactions(paymentTransactions);
        countExpenses(paymentTransactions);
        countIncome(transactionsData);
    }

    const countExpenses = (paymentTransactions: ITransaction[]) => {
        const expenses = paymentTransactions.reduce((total, transaction) => total + transaction.amount, 0);
        setExpenses(expenses);
    }

    const countIncome = (transactionsData: ITransaction[]) => {
        const income = transactionsData.filter(transaction => transaction.TransactionType === TransactionType.TOP_UP)
            .reduce((total, transaction) => total + transaction.amount, 0);
        setIncome(income);
    }

    useEffect(() => {
        transformTransactionsAndCalculateFinancials().then();
    }, []);

    return (
        <Card className="card-background card-payment card-graphic">
            <CardContent className="card-content">
                <Grid container spacing={2} direction="column">
                    <Grid item>
                        <Typography variant="h6">
                            {Content.TRANSACTIONS}
                        </Typography>
                    </Grid>
                    <Grid item>
                        <FinancialSummary income={income} expenses={expenses}/>
                    </Grid>
                </Grid>
            </CardContent>
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
                                    size: 15,
                                }
                            }
                        }
                    }
                }} data={getGraphData(transactions, expenses)} className="graphic"/>
                :
                <></>
            }
        </Card>
    );
};

export default Graph;