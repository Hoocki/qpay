import React from 'react';
import {Box} from "@mui/material";
import {Titles} from "../../../../../common/constansts/titles";
import Balance from "../balance/Balance";
import ScanPayEntry from "../../../../../components/scanPayEntry/ScanPayEntry";
import TopUpEntry from "../../../../../components/topupEntry/TopUpEntry";
import TransactionsEntry from "../../../../../components/transactionsEntry/TransactionsEntry";
import Title from "../../../../../components/title/Title";
import {useNavigate} from "react-router-dom";
import {Paths} from "../../../../../common/constansts/paths";

const ClientHome: React.FC = () => {
    const navigate = useNavigate();

    const handlePaymentClick = () => {
        navigate(Paths.PAYMENT);
    }

    const handleTopUpClick = () => {
        navigate(Paths.TOP_UP);
    }

    const handleHistoryClick = () => {
        navigate(Paths.TRANSACTIONS);
    }

    return (
        <Box>
            <Title title={Titles.HOME}/>
            <Balance/>
            <Box className="components-container">
                <ScanPayEntry handlePaymentClick={handlePaymentClick}/>
                <TopUpEntry handleTopUpClick={handleTopUpClick}/>
                <TransactionsEntry handleTransactionsClick={handleHistoryClick}/>
            </Box>
        </Box>
    );
};

export default ClientHome;