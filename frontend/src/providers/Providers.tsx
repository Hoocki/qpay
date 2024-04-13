import React from 'react';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import GlobalRoutes from "../routes/GlobalRoutes";
import {Provider} from "react-redux";
import {persistor, store} from "../stores/redux/store";
import {PersistGate} from 'redux-persist/integration/react';

const Providers: React.FC = () => {

    const router = createBrowserRouter([
        {
            path: "/*",
            element: <GlobalRoutes/>
        }
    ]);

    return (
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                <RouterProvider router={router}/>
            </PersistGate>
        </Provider>
    );
}

export default Providers;