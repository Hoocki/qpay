import {UserType} from "../types/user";
import {ITransactionsOutcome} from "../types/transactions";
import {mockTransactionsOutcome} from "./mock";

export const getTransactionsOutcomeInRange = async (userId: number, userType: UserType, startDate: Date, endDate: Date): Promise<ITransactionsOutcome> => {
    return mockTransactionsOutcome;
}