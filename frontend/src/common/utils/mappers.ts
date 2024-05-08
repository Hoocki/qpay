import {IQRCodeData} from "../../types/QRCodeCredentials";
import {ILoggedUser, IUser} from "../../types/user";
import {TokenData} from "../../types/TokenData";

export const createQrCodeData = (walletId: number, name: string, money: number): IQRCodeData => {
    return {
        name,
        walletId,
        money
    }
}

export const createLoggedUser = (token: string, user: IUser, decodedToken: TokenData): ILoggedUser => {
    return {
        id: user.id,
        email: user.email,
        name: user.name,
        userType: decodedToken.userType,
        token
    };
}