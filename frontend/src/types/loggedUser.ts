import {UserType} from "./userType";

export interface ILoggedUser {
    id: number
    name: string,
    email: string,
    token: string,
    userType: UserType,
}