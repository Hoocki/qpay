import {UserMenuProps} from "./props";
import React from "react";
import {Avatar, Box, IconButton, Menu, MenuItem, Tooltip, Typography} from "@mui/material";
import './styles.css';
import {useAppDispatch, useAppSelector} from "../../../stores/redux/hooks";
import {logOut, selectLoggedUser} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {Titles} from "../../../common/constansts/titles";
import {anchorOriginTopRight, transformOriginTopRight} from "../../../common/constansts/positions";
import ConfirmLogOut from "./confirmLogOut/ConfirmLogOut";

const UserMenu: React.FC<UserMenuProps> = ({anchorElUser, handleCloseMenu, handleOpenUserMenu, settingsTabs}: UserMenuProps) => {
    const loggedUser = useAppSelector(selectLoggedUser);
    const dispatch = useAppDispatch();
    const [showDialog, setShowDialog] = React.useState<boolean>(false);

    const handleClickOpen = () => {
        setShowDialog(true);
    };

    const handleClose = () => {
        setShowDialog(false);
    };

    const handleLogOut = () => {
        dispatch(logOut());
    };

    const handleCloseUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        const pageName = event.currentTarget.textContent ?? '';
        if (pageName === Titles.LOG_OUT) {
            handleClickOpen();
        }
        handleCloseMenu(event);
    }

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
                anchorOrigin={anchorOriginTopRight}
                keepMounted
                transformOrigin={transformOriginTopRight}
                open={Boolean(anchorElUser)}
                onClose={handleCloseMenu}
            >
                {settingsTabs.map((setting) => (
                    <MenuItem key={setting} onClick={handleCloseUserMenu}>
                        <Typography className="typography">{setting}</Typography>
                    </MenuItem>
                ))}
            </Menu>
            <ConfirmLogOut showDialog={showDialog} handleLogOut={handleLogOut} handleClose={handleClose}/>
        </Box>
    );
};

export default UserMenu;