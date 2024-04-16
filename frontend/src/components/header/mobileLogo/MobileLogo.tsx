import React from 'react';
import {Typography} from "@mui/material";
import './styles.css';
import {Titles} from "../../../common/constansts/titles";

const MobileLogo: React.FC = () => {
    return (
        <Typography
            variant="h5"
            noWrap
            component="a"
            className="mobile-logo mobile-logo-typography"
        >
            {Titles.APP_NAME}
        </Typography>
    );
};

export default MobileLogo;