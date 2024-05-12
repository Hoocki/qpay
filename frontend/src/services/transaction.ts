import {UserType} from "../types/user";
import {ITransaction, ITransactionsOutcome} from "../types/transactions";
import {mockEnabled, mockTransactions, mockTransactionsOutcome} from "./mock/mock";
import {TransactionEndpoints} from "../common/constansts/endpoints";
import httpClient from "./httpClient";
import _ from "lodash";

export const getTransactionsOutcomeInRange = async (userId: number, userType: UserType, startDate: Date, endDate: Date): Promise<ITransactionsOutcome> => {
    return mockTransactionsOutcome;
}

export const getTransactionsForPage = async (walletId: number, page: number, size: number, userType: UserType) => {
    if (mockEnabled) return mockTransactions;
    const targetPath = `${TransactionEndpoints.TRANSACTION}/${walletId}?page=${page}&size=${size}&userType=${userType}`;
    const response = await httpClient.get<ITransaction[]>(targetPath);
    return _.get(response, "data");
}