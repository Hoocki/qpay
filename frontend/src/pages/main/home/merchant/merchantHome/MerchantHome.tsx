import React, {useState} from 'react';
import {Box} from "@mui/material";
import PaymentPanel from "../paymentPanel/PaymentPanel";
import GenerateQR from "../qrGeneration/QrGeneration";
import "./styles.css";
import Title from '../../../../../components/title/Title';
import {Titles} from "../../../../../common/constansts/titles";

const MerchantHome = () => {
    const [qrCode, setQrCode] = useState<string>("");

    const updateQrCode = (qrCode: string) => {
        setQrCode(qrCode);
    }

    return (
        <Box className="merchant-home">
            <Title title={Titles.GENERATE_QR}/>
            <PaymentPanel updateQrCode={updateQrCode}/>
            <GenerateQR qrCode={qrCode}/>
        </Box>
    );
};

export default MerchantHome;