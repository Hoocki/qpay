import React, {useEffect, useState} from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import {IRoute, mainClientRoutes, mainMerchantRoutes} from "./routesConfig";
import {Paths} from "../common/constansts/paths";
import {useAppSelector} from "../stores/redux/hooks";
import {UserType} from "../types/user";
import {selectLoggedUserType} from "../stores/redux/loggedUser/loggedUserSlice";

const getInitialState = (userType: UserType) => userType === UserType.Customer ? mainClientRoutes : mainMerchantRoutes;

const RouteMain: React.FC = () => {

    const loggedUserType = useAppSelector(selectLoggedUserType);
    const [mainRoutes, setMainRoutes] = useState<IRoute[]>(getInitialState(loggedUserType));

    useEffect(() => {
        setMainRoutes(getInitialState(loggedUserType));
    }, [loggedUserType]);

    return (
        <Routes>
            {mainRoutes.map((route: IRoute) => (
                <Route key={route.path} path={route.path} element={route.element}/>
            ))}
            <Route path={Paths.ANY} element={<Navigate to={Paths.HOME} replace={true}/>}/>
        </Routes>
    );
}

export default RouteMain;