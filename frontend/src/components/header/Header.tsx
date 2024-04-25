import React, {useEffect, useState} from 'react';
import {NavigateFunction, useLocation, useNavigate} from "react-router-dom";
import {HeaderProps} from "./props";
import {IRoute, mainRoutes} from "../../routes/routesConfig";
import {AppBar, Container, Toolbar} from '@mui/material';
import DesktopLogo from "./desktopLogo/DesktopLogo";
import MobileMenu from "./mobileMenu/MobileMenu";
import MobileLogo from "./mobileLogo/MobileLogo";
import DesktopMenu from "./desktopMenu/DesktopMenu";
import UserMenu from "./userMenu/UserMenu";
import './styles.css';
import {Paths} from "../../common/constansts/paths";
import {useAppDispatch} from "../../stores/redux/hooks";
import {logOut} from "../../stores/redux/loggedUser/loggedUserSlice";
import {Titles} from "../../common/constansts/titles";

const Header: React.FC<HeaderProps> = ({isLogged}: HeaderProps) => {

    const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);
    const navigate = useNavigate();
    const dispatch = useAppDispatch();
    const location = useLocation();

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(null);
        setAnchorElUser(null);
        const pageName = event.currentTarget.textContent ?? '';
        const route = findRouteByPath(pageName);
        if (pageName === Titles.LOG_OUT) {
            dispatch(logOut());
        } else if (route) {
            navigate(route.path);
        }
    };

    const findRouteByPath = (path: string) => {
        const normalizedPath = path.toLowerCase().replace(/\s/g, '');
        return mainRoutes.find((route: IRoute) => route.path.trim().slice(1).toLowerCase() === normalizedPath);
    };

    const redirectToPage = (isLogged: boolean, navigate: NavigateFunction) => {
        if (isLogged) {
            navigate(Paths.Home);
        } else if (location.pathname !== Paths.SignUp) {
            navigate(Paths.SignIn);
        }
    };

    useEffect(() => {
        redirectToPage(isLogged, navigate);
    }, [isLogged]);

    return (
        <AppBar position="static" className="app-bar">
            {isLogged && (
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <DesktopLogo/>
                        <MobileMenu anchorEl={anchorElNav} handleCloseMenu={handleCloseMenu}
                                    handleOpenNavMenu={handleOpenNavMenu}/>
                        <MobileLogo/>
                        <DesktopMenu handleCloseMenu={handleCloseMenu}/>
                        <UserMenu anchorElUser={anchorElUser} handleCloseMenu={handleCloseMenu}
                                  handleOpenUserMenu={handleOpenUserMenu}/>
                    </Toolbar>
                </Container>
            )}
        </AppBar>
    );
}

export default Header;