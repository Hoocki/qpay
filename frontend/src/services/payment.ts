import {IPayment, IWalletTopUp} from "../types/payment";
import {IWallet} from "../types/wallet";
import {mockEnabled, mockWallet, mockWalletTopUp} from "./mock/mock";
import httpClient from "./httpClient";
import _ from "lodash";
import {PaymentEndpoints} from "../common/constansts/endpoints";

export const makePayment = async (payment: IPayment): Promise<IWallet> => {
    if (mockEnabled) return mockWallet;
    const targetPath = `${PaymentEndpoints.PAYMENT}${PaymentEndpoints.P2B}`;
    const response = await httpClient.post<IWallet>(targetPath, payment);
    return _.get(response, "data");
}

export const topUp = async (walletId: number, walletTopUp: IWalletTopUp) => {
    if (mockEnabled) return mockWalletTopUp;
    const targetPath = `${PaymentEndpoints.PAYMENT}${PaymentEndpoints.TOP_UP}/${walletId}`;
    const response = await httpClient.post<IWallet>(targetPath, walletTopUp);
    return _.get(response, "data");
}