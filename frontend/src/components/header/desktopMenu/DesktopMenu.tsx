import {Box, Button} from '@mui/material';
import React from 'react';
import {DesktopMenuProps} from "./props";
import './styles.css';
import {HEADER_MAIN_TABS} from "../../../common/constansts/headers";

const DesktopMenu: React.FC<DesktopMenuProps> = ({handleCloseMenu}: DesktopMenuProps) => {
    return (
        <Box className="desktop-menu">
            {HEADER_MAIN_TABS.map((page) => (
                <Button
                    key={page}
                    onClick={(event) => handleCloseMenu(event)}
                    className="page-button"
                >
                    {page}
                </Button>
            ))}
        </Box>
    );
};

export default DesktopMenu;