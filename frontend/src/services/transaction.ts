import {UserType} from "../types/user";
import {ITransactionData} from "../types/transactions";
import {mockTransactionsData} from "./mock";

export const getTransactionsInRange = async (userId: number, userType: UserType, startDate: Date, endDate: Date): Promise<ITransactionData> => {
    return mockTransactionsData;
}