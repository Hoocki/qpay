import React, {useEffect, useState} from 'react';
import {Box, Card, CardContent, Typography} from "@mui/material";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import "./styles.css";
import {IWallet} from "../../../../../../types/wallet";
import {getWalletByUserService} from "../../../../../../services/wallet";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import {CURRENCY_USD} from "../../../../../../common/constansts/currency";
import {Content} from "../../../../../../common/constansts/content";

const initialWallet: IWallet = {
    id: -1,
    balance: 0,
    userId: -1
}

const Balance: React.FC = () => {
    const loggedUser = useAppSelector(selectLoggedUser);
    const [wallet, setWallet] = useState<IWallet>(initialWallet)

    useEffect(() => {
        getWallet().then();
    }, []);

    const getWallet = async () => {
        const receivedWallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        setWallet(receivedWallet);
    }

    return (
        <Card className="card-background card-payment card-balance">
            <CardContent className="card-content">
                <AccountBalanceWalletIcon className="wallet-icon" color="primary"/>
                <Box className="balance-info">
                    <Typography className="wallet-balance">
                        {wallet.balance} {CURRENCY_USD}
                    </Typography>
                    <Typography className="wallet-title" color="text.secondary">
                        {Content.BALANCE}
                    </Typography>
                </Box>
            </CardContent>
        </Card>
    );
};

export default Balance;