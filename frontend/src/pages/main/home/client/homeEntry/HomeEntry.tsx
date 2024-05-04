import React from 'react';
import {Box, Card, CardActionArea, CardContent, Icon, Typography} from "@mui/material";
import {HomeEntryProps} from "./props";
import "../../../../../common/styles/icons.css";
import {useNavigate} from "react-router-dom";

const HomeEntry: React.FC<HomeEntryProps> = ({content, path, iconName}) => {

    const navigate = useNavigate();

    const handleCardClick = () => {
        navigate(path);
    }

    return (
        <Card className="card-payment">
            <CardActionArea onClick={handleCardClick}>
                <CardContent className="card-content">
                    <Box className="home-components-info">
                        <Icon color="primary" className="icon">{iconName}</Icon>
                        <Typography variant="h6">
                            {content}
                        </Typography>
                    </Box>
                </CardContent>
            </CardActionArea>
        </Card>
    );
};

export default HomeEntry;