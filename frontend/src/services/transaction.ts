import {UserType} from "../types/user";
import {mockTransactions} from "./mock";
import {Transaction} from "../types/transactions";

export const getTransactionForYearService = async (userId: number, userType: UserType, beginDate: number, endDate: number): Promise<Transaction[]> => {
    return mockTransactions;
}