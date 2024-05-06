import React, {useEffect, useState} from 'react';
import {Card, CardContent, Grid, Typography} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "./styles.css";
import "../balance/styles.css";
import {Pie} from "react-chartjs-2";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";
import {Content} from "../../../../../../common/constansts/content";
import {ITransactionGroup} from "../../../../../../types/transactions";
import {getChartTransactionsInRange} from "../../../../../../services/transaction";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import FinancialSummary from "../financialSummary/FinancialSummary";
import {getGraphData} from "./utils";
import {Time} from "../../../../../../common/constansts/time";

Chart.register(Tooltip, Legend, ArcElement);

const Graph: React.FC = () => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [transactions, setTransactions] = useState<ITransactionGroup[]>([]);
    const [income, setIncome] = useState<number>(0);
    const [expenses, setExpenses] = useState<number>(0);

    const getTransactionData = async () => {
        const transactionsData = await getChartTransactionsInRange(loggedUser.id, loggedUser.userType, Time.START_DATE, Time.END_DATE);
        const expensesTransactions = transactionsData.expenses.transactions;
        setTransactions(expensesTransactions);
        countExpenses(transactionsData.expenses.total);
        countIncome(transactionsData.income.total);
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
                }} data={getGraphData(transactions)} className="graphic"/>
                :
                <></>
            }
        </Card>
    );
};

export default Graph;