import React from 'react';
import {Box} from "@mui/material";
import {FinanceSummaryProps} from "./props";
import {CURRENCY_USD} from "../../../../../../../common/constansts/currency";
import TextContent from "../../../../../../../components/typography/textContent/TextContent";

const FinancialSummary: React.FC<FinanceSummaryProps> = ({income, expenses}) => {
    return (
        <Box>
            <TextContent text={`Income: ${income}${CURRENCY_USD}`}/>
            <TextContent text={`Expenses: ${expenses}${CURRENCY_USD}`}/>
        </Box>
    );
};

export default FinancialSummary;