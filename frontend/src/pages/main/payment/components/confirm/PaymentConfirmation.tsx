import React, {useEffect, useState} from 'react';
import {Box, Card, CardContent, Checkbox, FormControlLabel} from "@mui/material";
import {ConfirmProps} from "./props";
import "./styles.css";
import {Content} from "../../../../../common/constansts/content";
import PaymentInformation from "../../../../../components/fields/paymentInformation/PaymentInformation";
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
import ConfirmationButton from "../../../../../components/buttons/confirmationButton/ConfirmationButton";
import CancelButton from "../../../../../components/buttons/cancelButton/CancelButton";
import CardTitle from "../../../../../components/typography/cardTitle/CardTitle";
import {Titles} from "../../../../../common/constansts/titles";

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
        const resultPayment = await makePayment(paymentData);
        if (!resultPayment) return;
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
                <CardTitle title={Titles.INFORMATION}/>
                <PaymentInformation data={userWallet.balance} prefix={Content.BALANCE}/>
                <PaymentInformation data={qrCodeData.name} prefix={Content.RECIPIENT}/>
                <PaymentInformation data={qrCodeData.money} prefix={Content.AMOUNT}/>
                <FormControlLabel label={Content.SEND_NOTIFICATION} control={<Checkbox onClick={handleNotification}/>}/>
            </CardContent>
            <Box>
                <CancelButton handleClick={handleCancel}/>
                <ConfirmationButton handleClick={handleConfirm} isDisabled={false}/>
            </Box>
        </Card>
    );
};

export default PaymentConfirmation;