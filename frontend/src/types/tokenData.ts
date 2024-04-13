import {UserType} from "./userType";

export interface TokenData {
    userId: number,
    email: string,
    userType: UserType
}