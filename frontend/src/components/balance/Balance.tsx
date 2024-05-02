import React, {useEffect, useState} from 'react';
import {Box, Card, CardContent, Typography} from "@mui/material";
import {Titles} from "../../common/constansts/titles";
import {useAppSelector} from "../../stores/redux/hooks";
import "./styles.css";
import {IWallet} from "../../types/wallet";
import {getWalletByUserService} from "../../services/wallet";
import {selectLoggedUser} from "../../stores/redux/loggedUser/loggedUserSlice";
import {UserType} from "../../types/user";
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';

const initialWallet: IWallet = {
    id: -1,
    name: "",
    balance: 0,
    userId: -1,
    userType: UserType.Merchant
}

const Balance: React.FC = () => {
    const loggedUser = useAppSelector(selectLoggedUser);
    const [wallet, setWallet] = useState<IWallet>(initialWallet)

    useEffect(() => {
        getWallet().then();
    }, []);

    const getWallet = async () => {
        const serverWallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        setWallet(serverWallet);
    }

    return (
        <Card className="card-balance">
            <CardContent className="card-content">
                <AccountBalanceWalletIcon className="wallet-icon"/>
                <Box className="balance-info">
                    <Typography className="wallet-balance">
                        {wallet.balance} $
                    </Typography>
                    <Typography className="wallet-title" color="text.secondary">
                        {Titles.BALANCE}
                    </Typography>
                </Box>
            </CardContent>
        </Card>
    );
};

export default Balance;