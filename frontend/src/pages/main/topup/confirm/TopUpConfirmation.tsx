import React, {useState} from 'react';
import {Box, Card, CardContent, Checkbox, FormControlLabel, TextField} from "@mui/material";
import {Titles} from "../../../../common/constansts/titles";
import CardTitle from "../../../../components/typography/cardTitle/CardTitle";
import AmountField from "../../home/merchant/components/paymentPanel/amount/AmountField";
import {Content} from "../../../../common/constansts/content";
import CancelButton from "../../../../components/buttons/cancelButton/CancelButton";
import ConfirmationButton from "../../../../components/buttons/confirmationButton/ConfirmationButton";
import {Paths} from "../../../../common/constansts/paths";
import {useNavigate} from "react-router-dom";
import {topUp} from "../../../../services/payment";
import {IWalletTopUp} from "../../../../types/payment";
import {useAppDispatch, useAppSelector} from "../../../../stores/redux/hooks";
import {showNotification} from "../../../../stores/redux/notification/notificationSlice";
import {NotificationType} from "../../../../types/notification";
import {Notifications} from "../../../../common/constansts/notifications";
import "./styles.css";
import {selectWalletId} from "../../../../stores/redux/wallet/walletSlicer";
import {selectLoggedUser} from "../../../../stores/redux/loggedUser/loggedUserSlice";
import {DatePicker} from "@mui/x-date-pickers";
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import dayjs from "dayjs";

const initialWalletTopUp: IWalletTopUp = {
    amount: 0,
    email: "",
    sendNotification: false
}

const TopUpConfirmation: React.FC = () => {

    const loggedUser = useAppSelector(selectLoggedUser);
    const [walletTopUp, setWalletTopUp] = useState<IWalletTopUp>({...initialWalletTopUp, email: loggedUser.email});
    const [isAmountValid, setIsAmountValid] = useState<boolean>(false);
    const walletId = useAppSelector(selectWalletId);
    const navigate = useNavigate();
    const dispatch = useAppDispatch();

    const updateAmountChange = (amount: number) => {
        setWalletTopUp({...walletTopUp, amount});
    };

    const updateIsAmountValidChange = (isAmountValid: boolean) => {
        setIsAmountValid(isAmountValid);
    };

    const handleSendNotificationCheckbox = () => {
        setWalletTopUp({...walletTopUp, sendNotification: !walletTopUp.sendNotification})
    }

    const handleCancel = () => {
        navigate(Paths.ANY);
    }

    const handleConfirm = async () => {
        const receivedWallet = await topUp(walletId, walletTopUp);
        if (!receivedWallet) return;
        dispatch(showNotification({show: true, type: NotificationType.SUCCESS, message:Notifications.SUCCESSFUL_TOP_UP}));
        navigate(Paths.ANY);
    }

    return (
        <Card className="card-background card-payment">
            <CardContent className="card-confirm-content">
                <CardTitle title={Titles.CARD_INFORMATION}/>
                <TextField
                    placeholder={"Card name"}
                    variant="outlined"
                    margin="normal"
                    type={"text"}
                    fullWidth={true}
                />
                <TextField
                    placeholder={"Card number"}
                    variant="outlined"
                    margin="normal"
                    type={"number"}
                    fullWidth={true}
                />
                <Box className="privacy-data-card">
                    <Box className="date-picker">
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DatePicker
                                views={['year', 'month']}
                                label="Expiration date"
                                minDate={dayjs('2020-01-01')}
                                maxDate={dayjs('2030-12-31')}
                            />
                        </LocalizationProvider>
                    </Box>
                    <TextField
                        placeholder={"CCV"}
                        variant="outlined"
                        margin="normal"
                        type={"number"}
                        className="field-ccv"
                        fullWidth={false}
                    />
                </Box>
                <AmountField updateAmountField={updateAmountChange} updateIsValidAmountField={updateIsAmountValidChange}/>
                <FormControlLabel label={Content.SEND_NOTIFICATION} control={<Checkbox onClick={handleSendNotificationCheckbox}/>}/>
            </CardContent>
            <Box>
                <CancelButton handleClick={handleCancel}/>
                <ConfirmationButton handleClick={handleConfirm} isDisabled={!isAmountValid}/>
            </Box>
        </Card>
    );
};

export default TopUpConfirmation;