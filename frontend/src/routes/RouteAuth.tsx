import React from 'react';
import {Navigate, Route, Routes} from "react-router-dom";
import {authRoutes, IRoute} from "./routesConfig";
import {Paths} from "../common/constansts/paths";

const RouteAuth: React.FC = () => {
    return (
        <Routes>
            {authRoutes.map((route: IRoute) => (
                <Route key={route.path} path={route.path} element={route.element}/>
            ))}
            <Route path={Paths.ANY} element={<Navigate to={Paths.SIGN_IN} replace={true} />}/>
        </Routes>
    );
}

export default RouteAuth;