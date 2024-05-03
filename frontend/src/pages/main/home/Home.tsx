import React from 'react';
import "../../../common/styles/button.css";
import "./client/balance/styles.css";
import "../../../common/styles/titles.css";
import "../../../common/styles/container.css";
import {Box} from "@mui/material";
import {useAppSelector} from "../../../stores/redux/hooks";
import {selectLoggedUserType} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {UserType} from "../../../types/user";
import ClientHome from "./client/clientHome/ClientHome";
import MerchantHome from './merchant/merchantHome/MerchantHome';

const Home: React.FC = () => {
    const loggedUserType = useAppSelector(selectLoggedUserType);

    return (
        <Box className="main-container">
            <Box className="content-container">
                {loggedUserType === UserType.Customer ?
                    <ClientHome/>
                    :
                    <MerchantHome/>}
            </Box>
        </Box>
    );
}

export default Home;