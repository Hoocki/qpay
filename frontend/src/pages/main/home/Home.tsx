import React from 'react';
import {Titles} from "../../../common/constansts/titles";
import "../../../common/styles/button.css";
import "../../../components/balance/styles.css";
import {Box, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";
import {Paths} from "../../../common/constansts/paths";
import Balance from "../../../components/balance/Balance";
import Invoice from "../../../components/createInvoice/Invoice";
import "./styles.css";

const Home: React.FC = () => {
    const navigate = useNavigate();

    const handleCardClick = () => {
        navigate(Paths.PAYMENT);
    }

    return (
        <Box className="common-container">
            <Box className="common-content">
                <Typography
                    variant="h4"
                    className="home-title"
                >
                    {Titles.HOME}
                </Typography>
                <Balance/>
                <Invoice handleCardClick={handleCardClick}/>
            </Box>
        </Box>
    );
}

export default Home;