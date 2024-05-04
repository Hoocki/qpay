import {AuthCredentials} from "../types/authCredentials";
import httpClient from "./httpClient";
import _ from "lodash";
import {User, UserCredentials, UserType} from "../types/user";
import {mockEnabled, mockToken, mockUser} from "./mock";
import {AuthEndpoints, UserEndpoints} from "../common/constansts/endpoints";

export const logInService = async (authCredentials: AuthCredentials): Promise<string> => {
    if (mockEnabled) return mockToken;
    const response = await httpClient.post<string>(AuthEndpoints.AUTH + AuthEndpoints.SIGN_IN_USER, authCredentials);
    return _.get(response, "data.token", "");
}

export const signUpService = async (userCredentials: UserCredentials, userType: UserType): Promise<User | null> => {
    if (mockEnabled) return mockUser;
    const basePath = userType === UserType.Customer ? UserEndpoints.CUSTOMER :UserEndpoints.MERCHANT;
    const targetPath = basePath + UserEndpoints.SIGN_UP_USER;
    const response = await httpClient.post<User>(targetPath, userCredentials);
    return _.get(response, "data", null);
}