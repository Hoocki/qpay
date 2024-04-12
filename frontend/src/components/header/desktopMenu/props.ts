import React from "react";

export interface DesktopMenuProps {
    pages: string[];
    handleCloseMenu: (event: React.MouseEvent<HTMLElement>) => void;
}