import {IPayment} from "../types/payment";
import {IWallet} from "../types/wallet";
import {mockEnabled, mockWallet} from "./mock/mock";
import httpClient from "./httpClient";
import _ from "lodash";
import {PaymentEndpoints} from "../common/constansts/endpoints";

export const makePayment = async (payment: IPayment): Promise<IWallet> => {
    if (mockEnabled) return mockWallet;
    const targetPath = `${PaymentEndpoints.PAYMENT}${PaymentEndpoints.P2B}`;
    const response = await httpClient.post<IWallet>(targetPath, payment);
    return _.get(response, "data");
}