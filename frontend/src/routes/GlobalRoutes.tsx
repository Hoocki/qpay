import React from 'react';
import Header from "../components/header/Header";
import RouteMain from "./RouteMain";
import RouteAuth from "./RouteAuth";
import {useAppSelector} from "../stores/redux/hooks";
import Notification from "../components/notification/Notification";
import Footer from "../components/footer/Footer";
import {selectIsLogged} from "../stores/redux/loggedUser/loggedUserSlice";

const GlobalRoutes: React.FC = () => {
    const isLogged = useAppSelector(selectIsLogged);

    return (
        <div>
            <Header isLogged={isLogged}></Header>
            {isLogged ? <RouteMain/> : <RouteAuth/>}
            <Notification/>
            <Footer/>
        </div>
    );
};

export default GlobalRoutes;