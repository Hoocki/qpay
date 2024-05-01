import React from "react";

export interface UserMenuProps {
    anchorElUser: null | HTMLElement;
    handleCloseMenu: (event: React.MouseEvent<HTMLElement>) => void;
    handleOpenUserMenu: (event: React.MouseEvent<HTMLElement>) => void;
    settingsTabs: string[];
}