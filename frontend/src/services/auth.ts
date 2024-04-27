import {AuthCredentials} from "../types/authCredentials";
import httpClient from "./httpClient";
import {AuthPath} from "../common/constansts/paths";
import _ from "lodash";

export const logInService = async (authCredentials: AuthCredentials): Promise<string> => {
    const response = await httpClient.post<string>(AuthPath.AUTH + AuthPath.SIGN_IN_USER, authCredentials);
    return _.get(response, "data.token", "");
}