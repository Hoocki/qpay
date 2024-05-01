import {Box, Button} from '@mui/material';
import React from 'react';
import {DesktopMenuProps} from "./props";
import './styles.css';

const DesktopMenu: React.FC<DesktopMenuProps> = ({handleCloseMenu, mainTabs}) => {
    return (
        <Box className="desktop-menu">
            {mainTabs.map((page) => (
                <Button
                    key={page}
                    onClick={handleCloseMenu}
                    className="page-button"
                >
                    {page}
                </Button>
            ))}
        </Box>
    );
};

export default DesktopMenu;