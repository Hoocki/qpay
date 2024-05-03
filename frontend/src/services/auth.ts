import {AuthCredentials} from "../types/authCredentials";
import httpClient from "./httpClient";
import _ from "lodash";
import {User, UserCredentials, UserType} from "../types/user";
import {mockEnabled, mockToken, mockUser} from "./mock";
import {AuthEndPoints, UserEndPoints} from "../common/constansts/endPoints";

export const logInService = async (authCredentials: AuthCredentials): Promise<string> => {
    if (mockEnabled) return mockToken;
    const response = await httpClient.post<string>(AuthEndPoints.AUTH + AuthEndPoints.SIGN_IN_USER, authCredentials);
    return _.get(response, "data.token", "");
}

export const signUpService = async (userCredentials: UserCredentials, userType: UserType): Promise<User | null> => {
    if (mockEnabled) return mockUser;
    const basePath = userType === UserType.Customer ? UserEndPoints.CUSTOMER :UserEndPoints.MERCHANT;
    const targetPath = basePath + UserEndPoints.SIGN_UP_USER;
    const response = await httpClient.post<User>(targetPath, userCredentials);
    return _.get(response, "data", null);
}