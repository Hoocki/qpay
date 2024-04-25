import {TokenData} from "../types/tokenData";
import {UserType} from "../types/user";

export const decodeToken = (token: string): TokenData => {
    return {
        userId: 0,
        email: "user@gmail.com",
        userType: UserType.Customer
    };
}