import React, {useState} from 'react';
import {Box, Typography} from "@mui/material";
import {Titles} from "../../common/constansts/titles";
import PaymentEntry from "../paymentEntry/PaymentEntry";
import GenerateQR from "../generateQR/GenerateQR";
import "./styles.css";

const MerchantHome = () => {
    const [qrCode, setQrCode] = useState<string>("");

    const updateQrCode = (qrCode: string) => {
        setQrCode(qrCode);
    }

    return (
        <Box className="merchant-home">
            <Typography
                variant="h3"
                className="home-title"
            >
                {Titles.GENERATE_QR}
            </Typography>
            <PaymentEntry updateQrCode={updateQrCode}/>
            <GenerateQR qrCode={qrCode}/>
        </Box>
    );
};

export default MerchantHome;