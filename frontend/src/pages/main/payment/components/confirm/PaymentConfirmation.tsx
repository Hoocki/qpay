import React, {useEffect, useState} from 'react';
import {Box, Button, Card, CardContent, Checkbox, FormControlLabel} from "@mui/material";
import BalanceField from "../../../../../components/fields/balance/BalanceField";
import {ConfirmProps} from "./props";
import {Buttons} from "../../../../../common/constansts/buttons";
import NameField from "../../../../../components/fields/name/NameField";
import {DISABLE_FIELD} from "../../../../../common/constansts/fields";
import "./styles.css";
import TextContent from "../../../../../components/textContent/TextContent";
import {Content} from "../../../../../common/constansts/content";
import AmountFieldConfirmation from "../../../../../components/fields/amountConfirmation/AmountFieldConfirmation";
import {useNavigate} from "react-router-dom";
import {Paths} from "../../../../../common/constansts/paths";
import {makePayment} from "../../../../../services/payment";
import {getWalletByUserService} from "../../../../../services/wallet";
import {mapPaymentData} from "../../../../../common/utils/mappers";
import {useAppDispatch, useAppSelector} from "../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../stores/redux/loggedUser/loggedUserSlice";
import {showNotification} from "../../../../../stores/redux/notification/notificationSlice";
import {NotificationType} from "../../../../../types/notification";
import {Notifications} from "../../../../../common/constansts/notifications";
import {IWallet} from "../../../../../types/wallet";

const initialWallet: IWallet = {
    id: -1,
    balance: 0,
    userId: -1
}

const PaymentConfirmation: React.FC<ConfirmProps> = ({qrCodeData}) => {

    const navigate = useNavigate();
    const [sendNotification, setSendNotification] = useState<boolean>(false);
    const [userWallet, setUserWallet] = useState<IWallet>(initialWallet);
    const loggedUser = useAppSelector(selectLoggedUser);
    const dispatch = useAppDispatch();

    const handleCancel = () => {
        navigate(Paths.ANY);
    }

    const handleNotification = () => {
        setSendNotification(!sendNotification);
    }

    const getUserWallet = async () => {
        const receivedWallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        if (!receivedWallet) return;
        setUserWallet(receivedWallet);
    }

    const handleConfirm = async () => {
        const paymentData = mapPaymentData(
            loggedUser.email,
            userWallet.id,
            qrCodeData.email,
            qrCodeData.walletId,
            qrCodeData.money,
            sendNotification);
        await makePayment(paymentData);
        dispatch(showNotification({
            show: true,
            type: NotificationType.SUCCESS,
            message: Notifications.SUCCESSFUL_PAYMENT
        }));
        navigate(Paths.ANY);
    }

    useEffect(() => {
        getUserWallet().then();
    }, []);

    return (
        <Card className="card-background card-payment">
            <CardContent className="card-confirm-content">
                <TextContent text={Content.INFORMATION}/>
                <BalanceField/>
                <NameField defaultName={qrCodeData.name} updateNameFields={() => {
                }} isDisabled={DISABLE_FIELD}/>
                <AmountFieldConfirmation amount={qrCodeData.money}/>
                <FormControlLabel label={Content.SEND_NOTIFICATION} control={<Checkbox onClick={handleNotification}/>}/>
            </CardContent>
            <Box>
                <Button
                    onClick={handleCancel}
                    variant="contained"
                    className="button"
                    color="secondary"
                >
                    {Buttons.CANCEL}
                </Button>
                <Button
                    onClick={handleConfirm}
                    variant="contained"
                    className="button"
                >
                    {Buttons.CONFIRM}
                </Button>
            </Box>
        </Card>
    );
};

export default PaymentConfirmation;