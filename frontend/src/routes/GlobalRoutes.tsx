import React, {useEffect, useState} from 'react';
import Header from "../components/header/Header";
import RouteMain from "./RouteMain";
import RouteAuth from "./RouteAuth";
import {useAppSelector} from "../stores/redux/hooks";
import {selectLoggedUser} from "../stores/redux/loggedUser/loggedUserSlice";
import _ from "lodash";

const GlobalRoutes: React.FC = () => {

    const [isLogged, setIsLogged] = useState<boolean>(false);
    const loggedUser = useAppSelector(selectLoggedUser);

    const updateIsLogged = () => {
        setIsLogged(!_.isEmpty(loggedUser.token));
    }

    useEffect(() => {
        updateIsLogged();
    }, [loggedUser]);

    return (
        <div>
            <Header isLogged={isLogged}></Header>
            {isLogged ? <RouteMain/> : <RouteAuth/>}
        </div>
    );
};

export default GlobalRoutes;