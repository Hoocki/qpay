import React from 'react';
import {Typography} from "@mui/material";
import {TextContentProps} from "./props";

const TextContent: React.FC<TextContentProps> = ({text}) => {
    return (
        <Typography
            variant="body1"
        >
            {text}
        </Typography>
    );
};

export default TextContent;