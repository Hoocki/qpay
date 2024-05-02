import {UserType} from "./user";

export interface IWallet {
    id: number
    name: string,
    balance: number,
    userId: number,
    userType: UserType
}