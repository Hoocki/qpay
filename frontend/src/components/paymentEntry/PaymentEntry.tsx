import React, {useState} from 'react';
import {Box, Button, Card, CardContent, CardMedia} from "@mui/material";
import "../../common/styles/container.css";
import "../../common/styles/button.css";
import "../../common/styles/icons.css";
import "./styles.css";
import Logo from "../../img/Logo.png"
import Amount from "../fields/amount/Amount";
import {Buttons} from "../../common/constansts/buttons";
import {getQrCodeService} from "../../services/qrcode";
import {useAppSelector} from "../../stores/redux/hooks";
import {selectLoggedUser} from "../../stores/redux/loggedUser/loggedUserSlice";
import {QRCodeCredentials} from "../../types/QRCodeCredentials";
import {getWalletByUserService} from "../../services/wallet";
import {PaymentEntryProps} from "./props";


const PaymentEntry: React.FC<PaymentEntryProps> = ({updateQrCode}) => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [amount, setAmount] = useState<number>(0);
    const [isAmountValid, setIsAmountValid] = useState<boolean>(false);

    const updateAmountChange = (amount: number, isEmailValid: boolean) => {
        setAmount(amount);
        setIsAmountValid(isEmailValid);
    };

    const createQrCodeCredentials = (walletId: number): QRCodeCredentials => {
        return {
            name: loggedUser.name,
            walletId,
            money: amount
        }
    }

    const handleGenerateQr = async () => {
        if (!isAmountValid) return;
        const wallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        if (!wallet) return;
        const qrCodeCredentials = createQrCodeCredentials(wallet.id);
        const qrCode =  await getQrCodeService(qrCodeCredentials);
        if (!qrCode) return;
        updateQrCode(qrCode);
    }

    const handleCancel = () => {
        updateQrCode("");
    }

    return (
        <Card className="card-payment">
                <CardMedia
                    component="img"
                    className="card-logo"
                    image={Logo}
                />
                <CardContent className="card-payment-content">
                    <Amount updateAmountFields={updateAmountChange}/>
                </CardContent>
            <Box>
                <Button
                    onClick={handleCancel}
                    variant="contained"
                    className="clear-button"
                >
                    {Buttons.CLEAR}
                </Button>
                <Button
                    onClick={handleGenerateQr}
                    variant="contained"
                    className="generate-button"
                    disabled={!isAmountValid}
                >
                    {Buttons.GENERATE}
                </Button>
            </Box>
        </Card>
    );
};

export default PaymentEntry;