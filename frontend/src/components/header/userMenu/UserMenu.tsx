import {UserMenuProps} from "./props";
import React from "react";
import {Avatar, Box, IconButton, Menu, MenuItem, Tooltip, Typography} from "@mui/material";
import './styles.css';
import {useAppSelector} from "../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {HEADER_SETTINGS_TABS} from "../../../common/constansts/headers";
import {Titles} from "../../../common/constansts/titles";

const UserMenu: React.FC<UserMenuProps> = ({anchorElUser, handleCloseMenu, handleOpenUserMenu}: UserMenuProps) => {
    const loggedUser = useAppSelector(selectLoggedUser);

    return (
        <Box className="user-menu">
            <Typography
                component="p"
                className="typography-username"
            >
                {loggedUser.name}
            </Typography>
            <Tooltip title={Titles.SETTINGS_HINT}>
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
                {HEADER_SETTINGS_TABS.map((setting) => (
                    <MenuItem key={setting} onClick={(event) => handleCloseMenu(event)}>
                        <Typography className="typography">{setting}</Typography>
                    </MenuItem>
                ))}
            </Menu>
        </Box>
    );
};

export default UserMenu;