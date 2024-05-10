import React, {useEffect, useState} from 'react';
import {Card, CardContent, Grid} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "./styles.css";
import "../balance/styles.css";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";
import {ITransactionsOutcome} from "../../../../../../types/transactions";
import {getTransactionsOutcomeInRange} from "../../../../../../services/transaction";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import FinancialSummary from "./financialSummary/FinancialSummary";
import PieChart from "./pieChart/PieChart";
import {getEndOfCurrentDate, getStartOfTheYearDate} from "../../../../../../common/utils/time";
import CardTitle from "../../../../../../components/typography/cardTitle/CardTitle";
import {Titles} from "../../../../../../common/constansts/titles";

Chart.register(Tooltip, Legend, ArcElement);

const initialStateTransactionOutcome: ITransactionsOutcome = {
    expenses: {
        transactionGroups: [],
        total: 0
    },
    income: {
        transactionGroups: [],
        total: 0
    },
}

const TransactionsChart: React.FC = () => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [transactionsOutcome, setTransactionsOutcome] = useState<ITransactionsOutcome>(initialStateTransactionOutcome);

    const getTransactionsOutcome = async () => {
        const startDate = getStartOfTheYearDate();
        const endDate = getEndOfCurrentDate();
        const receivedTransactionsOutcome = await getTransactionsOutcomeInRange(loggedUser.id, loggedUser.userType, startDate, endDate);
        setTransactionsOutcome(receivedTransactionsOutcome);
    }

    useEffect(() => {
        getTransactionsOutcome().then();
    }, []);

    return (
        <Card className="card-background card-payment card-transaction-outcome">
            <CardContent className="card-content">
                <Grid container spacing={2} direction="column">
                    <Grid item>
                        <CardTitle title={Titles.TRANSACTIONS_FOR_YEAR}/>
                    </Grid>
                    <Grid item>
                        <FinancialSummary income={transactionsOutcome.income.total}
                                          expenses={transactionsOutcome.expenses.total}/>
                    </Grid>
                </Grid>
            </CardContent>
            <PieChart transactionGroups={transactionsOutcome.expenses.transactionGroups}
                      total={transactionsOutcome.expenses.total}/>
        </Card>
    );
};

export default TransactionsChart;