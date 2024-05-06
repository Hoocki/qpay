import {UserType} from "../types/user";
import {mockTransactions} from "./mock";
import {ITransaction} from "../types/transactions";

export const getTransactionsInRange = async (userId: number, userType: UserType, startDate: Date, endDate: Date): Promise<ITransaction[]> => {
    return mockTransactions;
}