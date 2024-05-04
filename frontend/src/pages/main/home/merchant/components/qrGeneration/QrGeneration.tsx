import React from 'react';
import {Box, Card, CardContent, Typography} from "@mui/material";
import QrCodeIcon from '@mui/icons-material/QrCode';
import "../../../../../../common/styles/icons.css";
import "./styles.css";
import "../../../../../../common/styles/container.css";
import {QRGenerationProps} from "./props";
import {Content} from "../../../../../../common/constansts/content";

const QrGeneration: React.FC<QRGenerationProps> = ({qrCode}) => {

    return (
        <Card className="card-background card-payment card-qr-generation">
            <CardContent className="card-qr-generation-content">
                {qrCode === "" ? <Box>
                        <QrCodeIcon className="icon-qr"/>
                        <Typography variant="h5">
                            {Content.ABOUT_QR}
                        </Typography>
                    </Box> :
                    <Typography variant="h5">
                        {qrCode}
                    </Typography>
                }
            </CardContent>
        </Card>
    );
};

export default QrGeneration;