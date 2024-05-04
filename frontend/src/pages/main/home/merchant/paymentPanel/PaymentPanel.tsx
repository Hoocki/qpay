import React, {useState} from 'react';
import {Box, Button, Card, CardContent, CardMedia} from "@mui/material";
import "../../../../../common/styles/container.css";
import "../../../../../common/styles/button.css";
import "../../../../../common/styles/icons.css";
import "./styles.css";
import Logo from "../../../../../img/Logo.png"
import Amount from "../../../../../components/fields/amount/Amount";
import {Buttons} from "../../../../../common/constansts/buttons";
import {getQrCodeService} from "../../../../../services/qrcode";
import {useAppSelector} from "../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../stores/redux/loggedUser/loggedUserSlice";
import {getWalletByUserService} from "../../../../../services/wallet";
import {PaymentPanelProps} from "./props";
import {createQrCodeCredentials} from "../../../../../common/mappers";

const PaymentPanel: React.FC<PaymentPanelProps> = ({updateQrCode}) => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [amount, setAmount] = useState<number>(0);
    const [isAmountValid, setIsAmountValid] = useState<boolean>(false);

    const updateAmountChange = (amount: number) => {
        setAmount(amount);
    };

    const updateIsAmountValidChange = (isAmountValid: boolean) => {
        setIsAmountValid(isAmountValid);
    };

    const handleGenerateQr = async () => {
        if (!isAmountValid) return;
        const wallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        if (!wallet) return;
        const qrCodeCredentials = createQrCodeCredentials(wallet.id, loggedUser.name, amount);
        const qrCode = await getQrCodeService(qrCodeCredentials);
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
                <Amount updateAmountField={updateAmountChange} updateIsValidAmountField={updateIsAmountValidChange}/>
            </CardContent>
            <Box>
                <Button
                    onClick={handleCancel}
                    variant="contained"
                    className="button"
                    color="secondary"
                >
                    {Buttons.CLEAR}
                </Button>
                <Button
                    onClick={handleGenerateQr}
                    variant="contained"
                    className="button"
                    disabled={!isAmountValid}
                >
                    {Buttons.GENERATE}
                </Button>
            </Box>
        </Card>
    );
};

export default PaymentPanel;