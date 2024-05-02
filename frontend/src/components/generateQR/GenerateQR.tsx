import React from 'react';
import {Box, Card, CardContent, Typography} from "@mui/material";
import QrCodeIcon from '@mui/icons-material/QrCode';
import {Titles} from "../../common/constansts/titles";
import "../../common/styles/icons.css";
import "./styles.css";
import "../../common/styles/container.css";
import {GenerateQRProps} from "./props";

const GenerateQr: React.FC<GenerateQRProps> = ({qrCode}) => {

    return (
        <Card className="card-generate">
            <CardContent className="card-generate-content">
                {qrCode === "" ? <Box>
                        <QrCodeIcon className="icon-qr"/>
                        <Typography variant="h5">
                            {Titles.ABOUT_QR}
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

export default GenerateQr;