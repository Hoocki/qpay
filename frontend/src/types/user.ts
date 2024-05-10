export interface IUser {
    id: number
    name: string,
    email: string,
    createdAt: Date,
    updatedAt: Date,
}

export const enum UserType {
    Customer = "CUSTOMER",
    Merchant = "MERCHANT"
}

export interface IUserCredentials {
    email: string,
    password: string,
    name: string
}

export interface ILoggedUser {
    id: number
    name: string,
    email: string,
    token: string,
    userType: UserType,
}