import {UserMenuProps} from "./props";
import React from "react";
import {Avatar, Box, IconButton, Menu, MenuItem, Tooltip, Typography} from "@mui/material";
import './styles.css';

const UserMenu: React.FC<UserMenuProps> = ({anchorElUser, handleCloseMenu, handleOpenUserMenu, settings}: UserMenuProps) => {
    return (
        <Box className="user-menu">
            <Tooltip title="Open settings">
                <IconButton
                    onClick={handleOpenUserMenu}
                    className="user-icon-button"
                >
                    <Avatar/>
                </IconButton>
            </Tooltip>
            <Menu
                className="user-settings-menu"
                id="menu-appbar"
                anchorEl={anchorElUser}
                anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseMenu}
            >
                {settings.map((setting) => (
                    <MenuItem key={setting} onClick={(event) => handleCloseMenu(event)}>
                        <Typography className="typography">{setting}</Typography>
                    </MenuItem>
                ))}
            </Menu>
        </Box>
    );
};

export default UserMenu;