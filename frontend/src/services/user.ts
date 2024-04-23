import {User, UserCredentials, UserType} from "../types/user";

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

export const signUpService = async (userCredentials: UserCredentials, userType: UserType): Promise<User> => {
    if (userType === UserType.Customer) {
        return {
            id: 1,
            email: "user@gmail.com",
            name: "User",
            createdAt: new Date(),
            updatedAt: new Date(),
        }
    }
    return {
        id: 1,
        email: "usermerchant@gmail.com",
        name: "UserMerchant",
        createdAt: new Date(),
        updatedAt: new Date(),
    }
}