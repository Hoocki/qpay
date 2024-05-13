import React from 'react';
import {Titles} from "../../../common/constansts/titles";
import {Box} from "@mui/material";
import Title from "../../../components/typography/title/Title";
import TopUpConfirmation from "./confirm/TopUpConfirmation";

const TopUp: React.FC = () => {

    return (
        <Box className="main-container">
            <Box className="content-container">
                <Title title={Titles.TOP_UP}/>
                <TopUpConfirmation/>
            </Box>
        </Box>
    );
}

export default TopUp;