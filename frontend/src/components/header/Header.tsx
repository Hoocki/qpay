import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {HeaderProps} from "./props";
import {IRoute, mainClientRoutes, mainMerchantRoutes} from "../../routes/routesConfig";
import {AppBar, Container, Toolbar} from '@mui/material';
import DesktopLogo from "./desktopLogo/DesktopLogo";
import MobileMenu from "./mobileMenu/MobileMenu";
import DesktopMenu from "./desktopMenu/DesktopMenu";
import UserMenu from "./userMenu/UserMenu";
import './styles.css';
import {useAppSelector} from "../../stores/redux/hooks";
import {UserType} from "../../types/user";
import {
    HEADER_CLIENT_MAIN_TABS,
    HEADER_CLIENT_SETTINGS_TABS,
    HEADER_MERCHANT_SETTINGS_TABS
} from "../../common/constansts/headers";
import {selectLoggedUserType} from "../../stores/redux/loggedUser/loggedUserSlice";

const getInitialStateMainRoutes = (userType: UserType) => userType === UserType.Customer ? mainClientRoutes : mainMerchantRoutes;
const getInitialStateMainTabs = (userType: UserType) => userType === UserType.Customer ? HEADER_CLIENT_MAIN_TABS : [];
const getInitialStateSettingsTabs = (userType: UserType) => userType === UserType.Customer ? HEADER_CLIENT_SETTINGS_TABS : HEADER_MERCHANT_SETTINGS_TABS;

const Header: React.FC<HeaderProps> = ({isLogged}: HeaderProps) => {

    const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);
    const navigate = useNavigate();
    const loggedUserType = useAppSelector(selectLoggedUserType);
    const [mainRoutes, setMainRoutes] = useState<IRoute[]>(getInitialStateMainRoutes(loggedUserType));
    const [mainTabs, setMainTabs] = useState<string[]>(getInitialStateMainTabs(loggedUserType));
    const [settingsTabs, setSettingsTabs] = useState<string[]>(getInitialStateSettingsTabs(loggedUserType));

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
        if (route) {
            navigate(route.path);
        }
    };

    const findRouteByPath = (path: string) => {
        const normalizedPath = path.toLowerCase().replace(/\s/g, '') === "home" ? "" :
            path.toLowerCase().replace(/\s/g, '');
        return mainRoutes.find((route: IRoute) => route.path.trim().slice(1).toLowerCase() === normalizedPath);
    };

    const updateTabs = () => {
        setMainTabs(getInitialStateMainTabs(loggedUserType));
        setSettingsTabs(getInitialStateSettingsTabs(loggedUserType));
        setMainRoutes(getInitialStateMainRoutes(loggedUserType));
    }

    useEffect(() => {
        updateTabs();
    }, [loggedUserType]);

    return (
        <AppBar className="app-bar">
            {isLogged && (
                <Container maxWidth={false} className="header-container">
                    <Toolbar disableGutters>
                        <DesktopLogo/>
                        <MobileMenu anchorEl={anchorElNav} handleCloseMenu={handleCloseMenu}
                                    handleOpenNavMenu={handleOpenNavMenu} mainTabs={mainTabs}/>
                        <DesktopMenu handleCloseMenu={handleCloseMenu} mainTabs={mainTabs}/>
                        <UserMenu anchorElUser={anchorElUser} handleCloseMenu={handleCloseMenu}
                                  handleOpenUserMenu={handleOpenUserMenu} settingsTabs={settingsTabs}/>
                    </Toolbar>
                </Container>
            )}
        </AppBar>
    );
}

export default Header;