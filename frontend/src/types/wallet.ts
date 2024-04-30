import {UserType} from "./user";

export interface Wallet {
    id: number
    name: string,
    balance: number,
    userId: number,
    userType: UserType,
    createdAt: Date,
    updatedAt: Date,
}

export interface IWallet {
    id: number
    name: string,
    balance: number,
    userId: number,
    userType: UserType
}