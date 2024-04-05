import {Box, Button} from '@mui/material';
import React from 'react';
import {DesktopMenuProps} from "./props";
import './styles.css';

const DesktopMenu: React.FC<DesktopMenuProps> = ({pages, handleCloseMenu}: DesktopMenuProps) => {
    return (
        <Box className="desktop-menu">
            {pages.map((page) => (
                <Button
                    key={page}
                    onClick={(event) => handleCloseMenu(event)}
                    className="button"
                >
                    {page}
                </Button>
            ))}
        </Box>
    );
};

export default DesktopMenu;