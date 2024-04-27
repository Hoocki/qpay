import {User, UserCredentials, UserType} from "../types/user";
import httpClient from "./httpClient";
import {UserPaths} from "../common/constansts/paths";
import _ from "lodash";

export const getUserService = async (userId: number, userType: UserType): Promise<User> => {
    const basePath = userType === UserType.Customer ? UserPaths.CUSTOMER : UserPaths.MERCHANT;
    const targetPath = `${basePath}/${userId}`;
    const response = await httpClient.get<User>(targetPath);
    return _.get(response, "data");
}

export const signUpService = async (userCredentials: UserCredentials, userType: UserType): Promise<User | null> => {
    const basePath = userType === UserType.Customer ? (UserPaths.CUSTOMER + UserPaths.SIGN_UP_USER) : (UserPaths.MERCHANT + UserPaths.SIGN_UP_USER);
    const response = await httpClient.post<User>(basePath, userCredentials);
    return _.get(response, "data", null);
}