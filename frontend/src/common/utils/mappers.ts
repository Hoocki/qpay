import {IQRCodeData} from "../../types/QRCodeCredentials";
import {ILoggedUser, IUser} from "../../types/user";
import {TokenData} from "../../types/TokenData";
import {IPayment} from "../../types/payment";

export const mapQrCodeData = (walletId: number, name: string, email: string, money: number): IQRCodeData => {
    return {
        name,
        walletId,
        email,
        money
    }
}

export const mapLoggedUser = (token: string, user: IUser, decodedToken: TokenData): ILoggedUser => {
    return {
        id: user.id,
        email: user.email,
        name: user.name,
        userType: decodedToken.userType,
        token
    };
}

export const mapPaymentData = (emailFrom: string, walletIdFrom: number, emailTo: string, walletIdTo: number, amount: number, sendNotification: boolean): IPayment => {
    return {
        emailFrom,
        walletIdFrom,
        emailTo,
        walletIdTo,
        amount,
        sendNotification
    }
}