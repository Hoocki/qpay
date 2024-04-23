import React from 'react';
import {Titles} from "../../../../common/constansts/titles";
import Logo from "../../../../img/Logo.png";
import {Box} from "@mui/material";
import "../../styles.css";

const AuthLogo = () => {
    return (
        <Box
            component="img"
            className="logo"
            alt={Titles.APP_NAME}
            src={Logo}
        />
    );
};

export default AuthLogo;