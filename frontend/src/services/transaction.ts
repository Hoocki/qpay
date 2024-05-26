import {UserType} from "../types/user";
import {ITransaction, ITransactionsOutcome} from "../types/transactions";
import {mockEnabled, mockTransactions, mockTransactionsOutcome} from "./mock/mock";
import {TransactionEndpoints} from "../common/constansts/endpoints";
import httpClient from "./httpClient";
import _ from "lodash";

export const getTransactionsOutcomeInRange = async (walletId: number, startDate: Date, endDate: Date): Promise<ITransactionsOutcome> => {
    if (mockEnabled) return mockTransactionsOutcome;
    const basePath = `${TransactionEndpoints.TRANSACTION}/${TransactionEndpoints.CHART}`;
    const startDateISO = startDate.toISOString();
    const endDateISO = endDate.toISOString();
    const targetPath = `${basePath}/${walletId}?startDate=${startDateISO}&endDate=${endDateISO}`;
    const response = await httpClient.get<ITransactionsOutcome>(targetPath);
    return _.get(response, "data");
}

export const getTransactionsForPage = async (walletId: number, page: number, size: number, userType: UserType) => {
    if (mockEnabled) return mockTransactions;
    const targetPath = `${TransactionEndpoints.TRANSACTION}/${walletId}?page=${page}&size=${size}&userType=${userType}`;
    const response = await httpClient.get<ITransaction[]>(targetPath);
    return _.get(response, "data");
}