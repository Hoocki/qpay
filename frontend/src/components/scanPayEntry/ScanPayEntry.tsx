import React from 'react';
import {Box, Card, CardActionArea, CardContent, Typography} from "@mui/material";
import CenterFocusWeakIcon from '@mui/icons-material/CenterFocusWeak';
import {Titles} from "../../common/constansts/titles";
import "../../common/styles/container.css";
import "../../common/styles/icons.css";
import {ScanPayEntryProps} from "./props";

const ScanPayEntry: React.FC<ScanPayEntryProps> = ({handlePaymentClick}) => {

    return (
        <Card className="card-payment">
            <CardActionArea onClick={handlePaymentClick}>
                <CardContent className="card-content">
                    <Box className="home-components-info">
                        <CenterFocusWeakIcon className="icon"/>
                        <Typography variant="h5">
                            {Titles.SCAN_PAY}
                        </Typography>
                    </Box>
                </CardContent>
            </CardActionArea>
        </Card>
    );
};

export default ScanPayEntry;