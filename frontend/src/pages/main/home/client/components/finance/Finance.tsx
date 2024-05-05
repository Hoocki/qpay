import React from 'react';
import {Box, Typography} from "@mui/material";
import {FinanceProps} from "./props";
import {CURRENCY_USD} from "../../../../../../common/constansts/currency";
import "./styles.css";

const Finance: React.FC<FinanceProps> = ({income, expenses}) => {
    return (
        <Box>
            <Typography
                variant="h6"
                className="finance"
            >
                Income: {income}{CURRENCY_USD}
            </Typography>
            <Typography
                variant="h6"
                className="finance"
            >
                Expenses: {expenses}{CURRENCY_USD}
            </Typography>
        </Box>
    );
};

export default Finance;