import {UserType} from "./user";

export interface TokenData {
    userId: number,
    email: string,
    userType: UserType
}