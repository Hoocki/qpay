import {UserType} from "../types/user";
import {mockEnabled, mockWallet} from "./mock";
import httpClient from "./httpClient";
import _ from "lodash";
import {IWallet} from "../types/wallet";
import {WalletEndpoints} from "../common/constansts/endpoints";

export const getWalletByUserService = async (userId: number, userType: UserType): Promise<IWallet> => {
    if (mockEnabled) return mockWallet;
    const targetPath = `${WalletEndpoints.WALLET}${WalletEndpoints.WALLET_BY_USER}/${userId}?userType=${userType}`;
    const response = await httpClient.get<IWallet>(targetPath);
    return _.get(response, "data");
}