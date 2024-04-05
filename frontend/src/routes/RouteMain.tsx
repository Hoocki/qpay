import React from 'react';
import {Route, Routes} from "react-router-dom";
import {IRoute, mainRoutes} from "./routesConfig";

const RouteMain: React.FC = () => {
    return (
        <Routes>
            {mainRoutes.map((route: IRoute) => (
                <Route key={route.path} path={route.path} element={route.element}/>
            ))}
        </Routes>
    );
}

export default RouteMain;