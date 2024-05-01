import React from "react";

export interface DesktopMenuProps {
    handleCloseMenu: (event: React.MouseEvent<HTMLElement>) => void;
    mainTabs: string[];
}