import React, {useEffect, useState} from 'react';
import {Titles} from "../../../common/constansts/titles";
import {Box} from "@mui/material";
import Title from "../../../components/typography/title/Title";
import TableTransactions from "./components/tableTransactions/TableTransactions";
import {IWallet} from "../../../types/wallet";
import {useAppSelector} from "../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {getWalletByUserService} from "../../../services/wallet";
import GeneratePdf from "./components/generatePdf/GeneratePdf";

const initialWallet: IWallet = {
    id: -1,
    balance: 0,
    userId: -1
}

const Transactions: React.FC = () => {

    const [wallet, setWallet] = useState<IWallet>(initialWallet);
    const loggedUser = useAppSelector(selectLoggedUser);

    const getWallet = async () => {
        const receivedWallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        if (!receivedWallet) return;
        setWallet(receivedWallet);
    }

    useEffect(() => {
        getWallet().then();
    }, []);

    return (
        <Box className="main-container">
            <Box className="content-container">
                <Title title={Titles.TRANSACTIONS}/>
                <TableTransactions walletId={wallet.id}/>
                <GeneratePdf/>
            </Box>
        </Box>
    );
}

export default Transactions;