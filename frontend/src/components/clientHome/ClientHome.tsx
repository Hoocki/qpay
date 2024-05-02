import React from 'react';
import {ClientHomeProps} from "./props";
import {Box, Typography} from "@mui/material";
import {Titles} from "../../common/constansts/titles";
import Balance from "../balance/Balance";
import ScanPayEntry from "../scanPayEntry/ScanPayEntry";
import TopUpEntry from "../topupEntry/TopUpEntry";
import History from "../history/History";

const ClientHome: React.FC<ClientHomeProps> = ({handlePaymentClick, handleTopUpClick, handleHistoryClick}) => {
    return (
        <Box>
            <Typography
                variant="h3"
                className="home-title"
            >
                {Titles.HOME}
            </Typography>
            <Balance/>
            <Box className="components-container">
                <ScanPayEntry handlePaymentClick={handlePaymentClick}/>
                <TopUpEntry handleTopUpClick={handleTopUpClick}/>
                <History handleHistoryClick={handleHistoryClick}/>
            </Box>
        </Box>
    );
};

export default ClientHome;