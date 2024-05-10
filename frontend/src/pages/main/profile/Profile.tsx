import React from 'react';
import {Titles} from "../../../common/constansts/titles";
import {Box} from "@mui/material";
import Title from "../../../components/typography/title/Title";
import ProfileInformation from "./components/profileInformation/ProfileInformation";

const Profile: React.FC = () => {
    return (
        <Box className="main-container">
            <Box className="content-container">
                <Title title={Titles.PROFILE}/>
                <ProfileInformation/>
            </Box>
        </Box>
    );
}

export default Profile;