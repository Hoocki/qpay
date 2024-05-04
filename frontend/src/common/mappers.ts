import {QRCodeCredentials} from "../types/QRCodeCredentials";
import {ILoggedUser, User} from "../types/user";
import {TokenData} from "../types/tokenData";

export const createQrCodeCredentials = (walletId: number, name: string, money: number): QRCodeCredentials => {
    return {
        name,
        walletId,
        money
    }
}

export const createLoggedUser = (token: string, user: User, decodedToken: TokenData): ILoggedUser => {
    return {
        id: user.id,
        email: user.email,
        name: user.name,
        userType: decodedToken.userType,
        token
    };
}