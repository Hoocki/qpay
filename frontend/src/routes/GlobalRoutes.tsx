import React, {useState} from 'react';
import Header from "../components/header/Header";
import RouteMain from "./RouteMain";
import RouteAuth from "./RouteAuth";

const GlobalRoutes: React.FC = () => {

    const [isLogged, setIsLogged] = useState<boolean>(true);

    return (
        <div>
            <Header isLogged={isLogged}></Header>
                {isLogged ? <RouteMain/> : <RouteAuth/>}
        </div>
    );
};

export default GlobalRoutes;