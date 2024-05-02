import React from 'react';
import {Box, Card, CardActionArea, CardContent, Typography} from "@mui/material";
import {Titles} from "../../common/constansts/titles";
import {TopUpEntryProps} from "./props";
import AddCardIcon from '@mui/icons-material/AddCard';
import "../../common/styles/icons.css";

const TopUpEntry: React.FC<TopUpEntryProps> = ({handleTopUpClick}) => {
    return (
        <Card className="card-payment">
            <CardActionArea onClick={handleTopUpClick}>
                <CardContent className="card-content">
                    <Box className="home-components-info">
                        <AddCardIcon className="icon"/>
                        <Typography variant="h5">
                            {Titles.TOP_UP}
                        </Typography>
                    </Box>
                </CardContent>
            </CardActionArea>
        </Card>
    );
};

export default TopUpEntry;