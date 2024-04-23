import React from 'react';
import {Route, Routes} from "react-router-dom";
import {authRoutes, IRoute} from "./routesConfig";

const RouteAuth: React.FC = () => {
    return (
        <Routes>
            {authRoutes.map((route: IRoute) => (
                <Route key={route.path} path={route.path} element={route.element}/>
            ))}
        </Routes>
    );
}

export default RouteAuth;