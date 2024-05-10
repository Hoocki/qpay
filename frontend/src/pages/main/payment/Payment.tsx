import React, {useState} from 'react';
import {Box} from "@mui/material";
import "./components/qrscan/styles.css";
import QrScan from "./components/qrscan/QrScan";
import {IQRCodeData} from "../../../types/QRCodeCredentials";
import Title from "../../../components/typography/title/Title";
import {Titles} from "../../../common/constansts/titles";
import PaymentConfirmation from "./components/confirm/PaymentConfirmation";

const initialStateQrCodeData: IQRCodeData = {
    walletId: -1,
    name: "",
    email: "",
    money: 0
}

const Payment: React.FC = () => {

    const [qrCodeData, setQrCodeData] = useState<IQRCodeData>(initialStateQrCodeData);

    const updateQrCodeData = (qrCodeData: IQRCodeData) => {
        setQrCodeData(qrCodeData);
    }

    return (
        <Box className="main-container">
            <Box className="content-container">
                {qrCodeData.walletId === -1
                    ? <QrScan updateQrCodeData={updateQrCodeData}/>
                    : <Box>
                        <Title title={Titles.CONFIRM_PURCHASE}/>
                        <PaymentConfirmation qrCodeData={qrCodeData}/>
                    </Box>
                }
            </Box>
        </Box>
    );
}

export default Payment;