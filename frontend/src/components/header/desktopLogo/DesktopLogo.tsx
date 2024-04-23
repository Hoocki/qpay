import React from 'react';
import Logo from "../../../img/Logo.png";
import {Box} from "@mui/material";
import './styles.css';
import {Titles} from "../../../common/constansts/titles";

const DesktopLogo: React.FC = () => {
    return (
        <Box
            component="img"
            className="desktop-logo"
            alt={Titles.APP_NAME}
            src={Logo}
        />
    );
};

export default DesktopLogo;