import React, {useState} from 'react';
import {Box, Card, CardContent, CardMedia} from "@mui/material";
import "../../../../../../common/styles/container.css";
import "../../../../../../common/styles/button.css";
import "../../../../../../common/styles/icons.css";
import "./styles.css";
import Logo from "../../../../../../img/Logo.png"
import AmountField from "./amount/AmountField";
import {Buttons} from "../../../../../../common/constansts/buttons";
import {getQrCodeService} from "../../../../../../services/qrcode";
import {useAppSelector} from "../../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../../stores/redux/loggedUser/loggedUserSlice";
import {PaymentPanelProps} from "./props";
import {mapQrCodeData} from "../../../../../../common/utils/mappers";
import CancelButton from "../../../../../../components/buttons/cancelButton/CancelButton";
import ConfirmationButton from "../../../../../../components/buttons/confirmationButton/ConfirmationButton";
import {selectWalletId} from "../../../../../../stores/redux/wallet/walletSlicer";

const PaymentPanel: React.FC<PaymentPanelProps> = ({updateQrCode}) => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const walletId = useAppSelector(selectWalletId);
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
        const qrCodeData = mapQrCodeData(walletId, loggedUser.name, loggedUser.email, amount);
        const qrCode = await getQrCodeService(qrCodeData);
        updateQrCode(qrCode);
    }

    const handleCancel = () => {
        updateQrCode("");
    }

    return (
        <Card className="card-background card-payment">
            <CardMedia
                component="img"
                className="card-logo"
                image={Logo}
            />
            <CardContent className="card-payment-content">
                <AmountField updateAmountField={updateAmountChange}
                             updateIsValidAmountField={updateIsAmountValidChange}/>
            </CardContent>
            <Box>
                <CancelButton buttonName={Buttons.CLEAR} handleClick={handleCancel}/>
                <ConfirmationButton buttonName={Buttons.GENERATE} handleClick={handleGenerateQr}
                                    isDisabled={!isAmountValid}
                />
            </Box>
        </Card>
    );
};

export default PaymentPanel;