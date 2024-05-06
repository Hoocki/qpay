import React, {useEffect, useState} from 'react';
import {Card, CardContent, Grid, Typography} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "./styles.css";
import "../balance/styles.css";
import {Pie} from "react-chartjs-2";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";
import {Content} from "../../../../../../common/constansts/content";
import {ITransaction} from "../../../../../../types/transactions";
import {getTransactionsInRange} from "../../../../../../services/transaction";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import FinancialSummary from "../financialSummary/FinancialSummary";
import {getGraphData} from "./utils";

Chart.register(Tooltip, Legend, ArcElement);

const Graph: React.FC = () => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [transactions, setTransactions] = useState<ITransaction[]>([]);
    const [income, setIncome] = useState<number>(0);
    const [expenses, setExpenses] = useState<number>(0);

    const getTransactionData = async () => {
        const transactionsData = await getTransactionsInRange(loggedUser.id, loggedUser.userType, new Date(), new Date());
        const paymentTransactions = transactionsData.expensesTransaction.transactions;
        setTransactions(paymentTransactions);
        countExpenses(transactionsData.expensesTransaction.amount);
        countIncome(transactionsData.incomeTransaction.amount);
    }

    const countExpenses = (amount: number) => {
        setExpenses(amount);
    }

    const countIncome = (amount: number) => {
        setIncome(amount);
    }

    useEffect(() => {
        getTransactionData().then();
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