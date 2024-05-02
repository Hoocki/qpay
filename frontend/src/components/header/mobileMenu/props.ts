import React from "react";

export interface MobileMenuProps {
    anchorEl: null | HTMLElement;
    mainTabs: string[];
    handleCloseMenu: (event: React.MouseEvent<HTMLElement>) => void;
    handleOpenNavMenu: (event: React.MouseEvent<HTMLElement>) => void;
}