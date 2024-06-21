import React from 'react';
import {Typography} from "@mui/material";
import {CardTitleProps} from "./props";

const CardTitle: React.FC<CardTitleProps> = ({title}) => {
    return (
        <Typography
            variant="h5"
        >
            {title}
        </Typography>
    );
};

export default CardTitle;