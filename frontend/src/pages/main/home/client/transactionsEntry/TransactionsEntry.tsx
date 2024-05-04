import React from 'react';
import {Box, Card, CardActionArea, CardContent, Typography} from "@mui/material";
import RestoreIcon from '@mui/icons-material/Restore';
import {Titles} from "../../../../../common/constansts/titles";
import {TransactionsEntryProps} from "./props";
import "../../../../../common/styles/icons.css";

const TransactionsEntry: React.FC<TransactionsEntryProps> = ({handleTransactionsClick}) => {
    return (
        <Card className="card-payment">
            <CardActionArea onClick={handleTransactionsClick}>
                <CardContent className="card-content">
                    <Box className="home-components-info">
                        <RestoreIcon className="icon"/>
                        <Typography variant="h5">
                            {Titles.TRANSACTIONS}
                        </Typography>
                    </Box>
                </CardContent>
            </CardActionArea>
        </Card>
    );
};

export default TransactionsEntry;