import React from 'react';
import "../../../common/styles/button.css";
import "../../../components/balance/styles.css";
import "./styles.css";
import "../../../common/styles/container.css";
import {Box} from "@mui/material";
import {useNavigate} from "react-router-dom";
import {Paths} from "../../../common/constansts/paths";
import {useAppSelector} from "../../../stores/redux/hooks";
import {selectLoggedUserType} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {UserType} from "../../../types/user";
import ClientHome from "../../../components/clientHome/ClientHome";
import MerchantHome from '../../../components/merchantHome/MerchantHome';

const Home: React.FC = () => {
    const loggedUserType = useAppSelector(selectLoggedUserType);
    const navigate = useNavigate();

    const handlePaymentClick = () => {
        navigate(Paths.PAYMENT);
    }

    const handleTopUpClick = () => {
        navigate(Paths.TOP_UP);
    }

    const handleHistoryClick = () => {
        navigate(Paths.TRANSACTIONS);
    }

    return (
        <Box className="main-container">
            <Box className="content-container">
                {loggedUserType === UserType.Customer ?
                    <ClientHome handlePaymentClick={handlePaymentClick}
                                handleTopUpClick={handleTopUpClick}
                                handleHistoryClick={handleHistoryClick}/>
                    :
                    <MerchantHome/>}
            </Box>
        </Box>
    );
}

export default Home;