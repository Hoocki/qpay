import React from 'react';
import {Typography} from "@mui/material";
import {TitleProps} from "./props";

const Title: React.FC<TitleProps> = ({title}) => {
    return (
        <Typography
            variant="h3"
            className="main-title"
        >
            {title}
        </Typography>
    );
};

export default Title;