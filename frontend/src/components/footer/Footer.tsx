import React from 'react';
import {Container, Typography} from "@mui/material";
import './styles.css';
import {FOOTER_CONTENT} from "../../common/constansts/footer";

const Footer: React.FC = () => {
    return (
        <Container maxWidth="lg" className="footer">
            <Typography variant="body2" >
                {FOOTER_CONTENT}
            </Typography>
        </Container>
    );
};

export default Footer;