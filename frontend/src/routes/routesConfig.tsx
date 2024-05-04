import SignIn from "../pages/auth/signin/SignIn";
import SignUp from "../pages/auth/signup/SignUp";
import React, {ReactNode} from "react";
import Profile from "../pages/main/profile/Profile";
import TopUp from "../pages/main/topup/TopUp";
import Transactions from "../pages/main/transactions/Transactions";
import Home from "../pages/main/home/Home";
import {Paths} from "../common/constansts/paths";
import Payment from "../pages/main/payment/Payment";

export interface IRoute {
    path: string
    element: ReactNode
}

export const mainClientRoutes: IRoute[] = [
    {
        path: Paths.PROFILE,
        element: <Profile/>
    },
    {
        path: Paths.TOP_UP,
        element: <TopUp/>
    },
    {
        path: Paths.TRANSACTIONS,
        element: <Transactions/>
    },
    {
        path: Paths.HOME,
        element: <Home/>
    },
    {
        path: Paths.PAYMENT,
        element: <Payment/>
    }
];

export const mainMerchantRoutes: IRoute[] = [
    {
        path: Paths.HOME,
        element: <Home/>
    }
];

export const authRoutes: IRoute[] = [
    {
        path: Paths.SIGN_IN,
        element: <SignIn/>
    },
    {
        path: Paths.SIGN_UP,
        element: <SignUp/>
    }
];
