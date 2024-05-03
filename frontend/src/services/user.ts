import {User, UserType} from "../types/user";
import httpClient from "./httpClient";
import _ from "lodash";
import {mockEnabled, mockUser} from "./mock";
import {UserEndPoints} from "../common/constansts/endPoints";

export const getUserService = async (userId: number, userType: UserType): Promise<User> => {
    if (mockEnabled) return mockUser;
    const basePath = userType === UserType.Customer ? UserEndPoints.CUSTOMER : UserEndPoints.MERCHANT;
    const targetPath = `${basePath}/${userId}`;
    const response = await httpClient.get<User>(targetPath);
    return _.get(response, "data");
}