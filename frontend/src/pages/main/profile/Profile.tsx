import React from 'react';
import {Titles} from "../../../common/constansts/titles";
import {Box} from "@mui/material";
import Title from "../../../components/typography/title/Title";
import ProfileInformation from "./components/profileInformation/ProfileInformation";
import "./styles.css"

const Profile: React.FC = () => {
    return (
        <Box className="main-container">
            <Box className="content-container">
                <Box className="profile-box">
                    <Title title={Titles.PROFILE}/>
                    <ProfileInformation/>
                </Box>
            </Box>
        </Box>
    );
}

export default Profile;