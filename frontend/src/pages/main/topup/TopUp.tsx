import React from 'react';
import {Box} from "@mui/material";
import TopUpConfirmation from "./confirm/TopUpConfirmation";
import Title from "../../../components/typography/title/Title";
import {Titles} from "../../../common/constansts/titles";
import "./styles.css"

const TopUp: React.FC = () => {

    return (
        <Box className="main-container">
            <Box className="content-container">
                <Box className="top-up-box">
                    <Title title={Titles.TOP_UP}/>
                    <TopUpConfirmation/>
                </Box>
            </Box>
        </Box>
    );
}

export default TopUp;