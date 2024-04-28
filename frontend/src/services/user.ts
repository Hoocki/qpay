import {User, UserType} from "../types/user";
import httpClient from "./httpClient";
import {UserPaths} from "../common/constansts/paths";
import _ from "lodash";
import {mockEnabled, mockUser} from "./mock";

export const getUserService = async (userId: number, userType: UserType): Promise<User> => {
    if (mockEnabled) return mockUser;
    const basePath = userType === UserType.Customer ? UserPaths.CUSTOMER : UserPaths.MERCHANT;
    const targetPath = `${basePath}/${userId}`;
    const response = await httpClient.get<User>(targetPath);
    return _.get(response, "data");
}