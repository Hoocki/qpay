import {User} from "../types/user";
import {UserType} from "../types/userType";

export const getUserService = async (userId: number, userType: UserType): Promise<User> => {
    if (userType === UserType.Customer) {
        return {
            id: userId,
            email: "user@gmail.com",
            name: "User",
            createdAt: new Date(),
            updatedAt: new Date(),
        }
    }
    return {
        id: userId,
        email: "usermerchant@gmail.com",
        name: "UserMerchant",
        createdAt: new Date(),
        updatedAt: new Date(),
    }
}