import React, {useEffect, useState} from 'react';
import {Card, CardContent, Grid} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "./styles.css";
import "../balance/styles.css";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";
import {ITransactionsOutcome} from "../../../../../../types/transactions";
import {getTransactionsOutcomeInRange} from "../../../../../../services/transaction";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import FinancialSummary from "./financialSummary/FinancialSummary";
import PieChart from "./pieChart/PieChart";
import {getEndOfCurrentDate, getStartOfTheYearDate} from "../../../../../../common/utils/time";
import CardTitle from "../../../../../../components/typography/cardTitle/CardTitle";
import {Titles} from "../../../../../../common/constansts/titles";
import {selectWalletId} from "../../../../../../stores/redux/wallet/walletSlicer";

Chart.register(Tooltip, Legend, ArcElement);

const initialStateTransactionOutcome: ITransactionsOutcome = {
    expenses: {
        transactionsGroup: [],
        total: 0
    },
    income: {
        transactionsGroup: [],
        total: 0
    },
}

const TransactionsChart: React.FC = () => {

    const walletId = useAppSelector(selectWalletId);
    const [transactionsOutcome, setTransactionsOutcome] = useState<ITransactionsOutcome>(initialStateTransactionOutcome);

    const getTransactionsOutcome = async () => {
        const startDate = getStartOfTheYearDate();
        const endDate = getEndOfCurrentDate();
        const receivedTransactionsOutcome = await getTransactionsOutcomeInRange(walletId, startDate, endDate);
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
            <PieChart transactionsGroup={transactionsOutcome.expenses.transactionsGroup}
                      total={transactionsOutcome.expenses.total}/>
        </Card>
    );
};

export default TransactionsChart;