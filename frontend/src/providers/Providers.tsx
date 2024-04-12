import React from 'react';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import GlobalRoutes from "../routes/GlobalRoutes";

const Providers: React.FC = () => {

    const router = createBrowserRouter([
        {
            path: "/*",
            element: <GlobalRoutes/>
        },
    ]);

    return (
        <RouterProvider router={router} />
    );
}

export default Providers;