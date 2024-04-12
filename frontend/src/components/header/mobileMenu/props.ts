import React from "react";

export interface MobileMenuProps {
    anchorEl: null | HTMLElement;
    handleCloseMenu: (event: React.MouseEvent<HTMLElement>) => void;
    handleOpenNavMenu: (event: React.MouseEvent<HTMLElement>) => void;
    pages: string[];
}