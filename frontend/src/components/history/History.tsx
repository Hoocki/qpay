import React from 'react';
import {Box, Card, CardActionArea, CardContent, Typography} from "@mui/material";
import RestoreIcon from '@mui/icons-material/Restore';
import {Titles} from "../../common/constansts/titles";
import {HistoryProps} from "./props";
import "../../common/styles/icons.css";

const History: React.FC<HistoryProps> = ({handleHistoryClick}) => {
    return (
        <Card className="card-payment">
            <CardActionArea onClick={handleHistoryClick}>
                <CardContent className="card-content">
                    <Box className="home-components-info">
                        <RestoreIcon className="icon"/>
                        <Typography variant="h5">
                            {Titles.HISTORY}
                        </Typography>
                    </Box>
                </CardContent>
            </CardActionArea>
        </Card>
    );
};

export default History;