import SignIn from "../pages/auth/signin/SignIn";
import SignUp from "../pages/auth/signup/SignUp";
import React, {ReactNode} from "react";
import Profile from "../pages/main/profile/Profile";
import TopUp from "../pages/main/topup/TopUp";
import Transactions from "../pages/main/transactions/Transactions";
import Home from "../pages/main/home/Home";
import {Paths} from "../constansts/paths";

export interface IRoute {
    path: string
    element: ReactNode
}

export const mainRoutes: IRoute[] = [
    {
        path: Paths.Profile,
        element: <Profile/>
    },
    {
        path: Paths.TopUp,
        element: <TopUp/>
    },
    {
        path: Paths.Transactions,
        element: <Transactions/>
    },
    {
        path: Paths.Home,
        element: <Home/>
    }
];

export const authRoutes: IRoute[] = [
    {
        path: Paths.SignIn,
        element: <SignIn/>
    },
    {
        path: Paths.SignUp,
        element: <SignUp/>
    }
];
