import React, {useEffect, useState} from 'react';
import {NavigateFunction, useNavigate} from "react-router-dom";
import {HeaderProps} from "./props";
import {IRoute, mainRoutes} from "../../routes/routesConfig";
import {AppBar, Container, StyledEngineProvider, Toolbar} from '@mui/material';
import DesktopLogo from "./desktopLogo/DesktopLogo";
import MobileMenu from "./mobileMenu/MobileMenu";
import MobileLogo from "./mobileLogo/MobileLogo";
import DesktopMenu from "./desktopMenu/DesktopMenu";
import UserMenu from "./userMenu/UserMenu";
import './styles.css';
import {Paths} from "../../constansts/paths";
import {HEADER_MAIN_TABS, HEADER_SETTINGS_TABS} from "../../constansts/headers";

const Header: React.FC<HeaderProps> = ({isLogged}: HeaderProps) => {

    const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);
    const navigate = useNavigate();

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(null);
        setAnchorElUser(null);
        const component = event.currentTarget.textContent ?? '';
        const route = findRouteByPath(component);
        if (route) {
            navigate(route.path);
        }
    };

    const findRouteByPath = (path: string) => {
        const normalizedPath = path.toLowerCase().replace(/\s/g, '');
        return mainRoutes.find((route: IRoute) => route.path.trim().slice(1).toLowerCase() === normalizedPath);
    };

    const redirectToPage = (isLogged: boolean, navigate: NavigateFunction) => {
        const path = isLogged ? Paths.Home : Paths.SignIn;
        navigate(path);
    };

    useEffect(() => {
        redirectToPage(isLogged, navigate);
    }, [isLogged, navigate]);

    return (
        <StyledEngineProvider injectFirst>
            <AppBar position="static" className="app-bar">
                {isLogged && (
                    <Container maxWidth="xl">
                        <Toolbar disableGutters>
                            <DesktopLogo/>
                            <MobileMenu anchorEl={anchorElNav} handleCloseMenu={handleCloseMenu}
                                        handleOpenNavMenu={handleOpenNavMenu} pages={HEADER_MAIN_TABS}/>
                            <MobileLogo/>
                            <DesktopMenu pages={HEADER_MAIN_TABS} handleCloseMenu={handleCloseMenu}/>
                            <UserMenu anchorElUser={anchorElUser} handleCloseMenu={handleCloseMenu}
                                      handleOpenUserMenu={handleOpenUserMenu} settings={HEADER_SETTINGS_TABS}/>
                        </Toolbar>
                    </Container>
                )}
            </AppBar>
        </StyledEngineProvider>
    );
}

export default Header;