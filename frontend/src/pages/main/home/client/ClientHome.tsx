import React from 'react';
import {Box} from "@mui/material";
import {Titles} from "../../../../common/constansts/titles";
import Balance from "./components/balance/Balance";
import Title from "../../../../components/title/Title";
import {Paths} from "../../../../common/constansts/paths";
import "./styles.css";
import {Icons} from "../../../../common/constansts/icons";
import HomeEntry from "./components/homeEntry/HomeEntry";
import TransactionsChart from "./components/chart/TransactionsChart";

const ClientHome: React.FC = () => {

    return (
        <Box className="client-home">
            <Title title={Titles.HOME}/>
            <Balance/>
            <Box className="components-container">
                <HomeEntry content={Titles.SCAN_PAY} path={Paths.PAYMENT} iconName={Icons.CENTER_FOCUS_WEAK}/>
                <HomeEntry content={Titles.TOP_UP} path={Paths.TOP_UP} iconName={Icons.ADD_CARD}/>
                <HomeEntry content={Titles.TRANSACTIONS} path={Paths.TRANSACTIONS} iconName={Icons.RESTORE}/>
            </Box>
            <TransactionsChart/>
        </Box>
    );
};

export default ClientHome;