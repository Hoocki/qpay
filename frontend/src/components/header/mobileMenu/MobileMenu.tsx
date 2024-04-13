import React from 'react';
import {Box, IconButton, Menu, MenuItem, Typography } from '@mui/material';
import {MobileMenuProps} from "./props";
import MenuIcon from "@mui/icons-material/Menu";
import './styles.css';
import {HEADER_MAIN_TABS} from "../../../common/constansts/headers";

const MobileMenu: React.FC<MobileMenuProps> = ({ anchorEl, handleCloseMenu, handleOpenNavMenu }: MobileMenuProps) => {
    return (
        <Box className="mobile-menu" >
            <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleOpenNavMenu}
                color="inherit"
            >
                <MenuIcon className="mobile-menu-icon-button" />
            </IconButton>
            <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                keepMounted
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                }}
                open={Boolean(anchorEl)}
                onClose={handleCloseMenu}
                className="settings-menu"
            >
                {HEADER_MAIN_TABS.map((page) => (
                    <MenuItem key={page} onClick={(event) => handleCloseMenu(event)}>
                        <Typography className="typography">{page}</Typography>
                    </MenuItem>
                ))}
            </Menu>
        </Box>
    );
};

export default MobileMenu;
