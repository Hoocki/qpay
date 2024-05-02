import React from 'react';
import Logo from "../../../img/Logo.png";
import {Box, IconButton} from "@mui/material";
import './styles.css';
import {Titles} from "../../../common/constansts/titles";
import {useNavigate} from "react-router-dom";
import {Paths} from "../../../common/constansts/paths";

const DesktopLogo: React.FC = () => {
    const navigate = useNavigate();

    const handleLogoClick = () => {
        navigate(Paths.HOME);
    }

    return (
        <IconButton
            onClick={handleLogoClick}
        >
            <Box
                component="img"
                className="desktop-logo"
                alt={Titles.APP_NAME}
                src={Logo}
            />
        </IconButton>
    );
};

export default DesktopLogo;