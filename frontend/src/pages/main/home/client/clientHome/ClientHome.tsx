import React from 'react';
import {Box} from "@mui/material";
import {Titles} from "../../../../../common/constansts/titles";
import Balance from "../balance/Balance";
import Title from "../../../../../components/title/Title";
import {Paths} from "../../../../../common/constansts/paths";
import "./styles.css";
import HomeEntry from "../homeEntry/HomeEntry";
import {Content} from "../../../../../common/constansts/content";
import {Icons} from "../../../../../common/constansts/icons";

const ClientHome: React.FC = () => {

    return (
        <Box className="client-home">
            <Title title={Titles.HOME}/>
            <Balance/>
            <Box className="components-container">
                <HomeEntry content={Content.SCAN_PAY} path={Paths.PAYMENT} iconName={Icons.CENTER_FOCUS_WEAK}/>
                <HomeEntry content={Titles.TOP_UP} path={Paths.TOP_UP} iconName={Icons.ADD_CARD}/>
                <HomeEntry content={Titles.TRANSACTIONS} path={Paths.TRANSACTIONS} iconName={Icons.RESTORE}/>
            </Box>
        </Box>
    );
};

export default ClientHome;