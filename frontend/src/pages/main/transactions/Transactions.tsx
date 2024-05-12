import React from 'react';
import {Titles} from "../../../common/constansts/titles";
import {Box} from "@mui/material";
import Title from "../../../components/typography/title/Title";
import TableTransactions from "./components/tableTransactions/TableTransactions";
import GeneratePdf from "./components/generatePdf/GeneratePdf";

const Transactions: React.FC = () => {

    return (
        <Box className="main-container">
            <Box className="content-container">
                <Title title={Titles.TRANSACTIONS}/>
                <TableTransactions/>
                <GeneratePdf/>
            </Box>
        </Box>
    );
}

export default Transactions;