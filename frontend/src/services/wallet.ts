import {UserType} from "../types/user";
import {mockEnabled, mockWallet} from "./mock";
import {WalletPaths} from "../common/constansts/paths";
import httpClient from "./httpClient";
import _ from "lodash";
import {Wallet} from "../types/wallet";

export const getWalletByUserService = async (userId: number, userType: UserType): Promise<Wallet> => {
    if (mockEnabled) return mockWallet;
    const basePath = WalletPaths.WALLET;
    const targetPath = `${basePath + WalletPaths.WALLET_BY_USER}/${userId}?userType=${userType}`;
    const response = await httpClient.get<Wallet>(targetPath);
    return _.get(response, "data");
}