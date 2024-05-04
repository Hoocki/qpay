import {User, UserType} from "../types/user";
import httpClient from "./httpClient";
import _ from "lodash";
import {mockEnabled, mockUser} from "./mock";
import {UserEndpoints} from "../common/constansts/endpoints";

export const getUserService = async (userId: number, userType: UserType): Promise<User> => {
    if (mockEnabled) return mockUser;
    const basePath = userType === UserType.Customer ? UserEndpoints.CUSTOMER : UserEndpoints.MERCHANT;
    const targetPath = `${basePath}/${userId}`;
    const response = await httpClient.get<User>(targetPath);
    return _.get(response, "data");
}