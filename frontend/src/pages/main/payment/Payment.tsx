import React, {useState} from 'react';
import {Box, Typography} from "@mui/material";
import "./components/qrscan/styles.css";
import QrScan from "./components/qrscan/QrScan";
import {IQRCodeData} from "../../../types/QRCodeCredentials";
import Title from "../../../components/title/Title";
import {Titles} from "../../../common/constansts/titles";

const initialStateQrCodeData: IQRCodeData = {
    walletId: -1,
    name: "",
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
                <Title title={Titles.PAYMENT}/>
                {qrCodeData.walletId === -1
                    ? <QrScan updateQrCodeData={updateQrCodeData}/>
                    : <Typography>{"Qr Code data: " + qrCodeData.name + " " + qrCodeData.money + " " + qrCodeData.walletId}</Typography>
                }
            </Box>
        </Box>
    );
}

export default Payment;