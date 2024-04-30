import React from 'react';
import {Box, Card, CardActionArea, CardContent, Typography} from "@mui/material";
import QrCode2Icon from "@mui/icons-material/QrCode2";
import {Titles} from "../../common/constansts/titles";
import {InvoiceProps} from "./props";
import "./styles.css";

const Invoice: React.FC<InvoiceProps> = ({handleCardClick}) => {
    return (
        <Card className="card-payment" >
            <CardActionArea onClick={handleCardClick}>
                <CardContent className="content-wrapper">
                    <Box className="left-content">
                        <QrCode2Icon className="qr-code"/>
                    </Box>
                    <Box className="right-content" sx={{ padding: '1rem' }}>
                        <Typography variant="h5">
                            {Titles.CREATE_INVOICE}
                        </Typography>
                    </Box>
                </CardContent>
            </CardActionArea>
        </Card>
    );
};

export default Invoice;