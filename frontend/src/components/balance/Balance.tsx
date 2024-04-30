import React from 'react';
import {Box, Card, CardContent, Typography} from "@mui/material";
import {Titles} from "../../common/constansts/titles";
import {useAppSelector} from "../../stores/redux/hooks";
import {selectWallet} from "../../stores/redux/wallet/walletSlice";
import "./styles.css";

const Balance: React.FC = () => {
    const wallet = useAppSelector(selectWallet);

    return (
        <Card className="card-balance">
            <CardContent className="content-wrapper">
                <Box className="left-content">
                    <Typography variant="h5" className="wallet-balance">
                        {wallet.balance} $
                    </Typography>
                </Box>
                <Box className="right-content">
                    <Typography variant="h5">
                        {Titles.BALANCE}
                    </Typography>
                </Box>
            </CardContent>
        </Card>
    );
};

export default Balance;