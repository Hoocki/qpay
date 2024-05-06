import {IUser, UserType} from "../types/user";
import {IWallet} from "../types/wallet";
import {
    IExpenseTransaction, IIncome,
    ITransactionData,
} from "../types/transactions";

// test configs
export const mockEnabled = true;
export const mockUserType = UserType.Customer as UserType;
//

const mockTokenMerchant = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6Ik1FUkNIQU5UIiwidXNlcklkIjoxNCwiZW1haWwiOiJ1c2VydGVzdDEwQG1haWwucnUiLCJzdWIiOiJ1c2VydGVzdDEwQG1haWwucnUiLCJpYXQiOjE3MTQ3OTUzNzV9.QtmHScrPj-G2XTJvZRcft6vjR2NJ8A360pmculfTc_0";
const mockTokenClient = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6IkNVU1RPTUVSIiwidXNlcklkIjo5MSwiZW1haWwiOiJ1c2VydGVzdDVAbWFpbC5ydSIsInN1YiI6InVzZXJ0ZXN0NUBtYWlsLnJ1IiwiaWF0IjoxNzE0Nzk1MzI3fQ.INChxr6QbqP_xmjZf77O9vTLv3UsmjF6PcmLonL05gM";

export const mockToken = mockUserType === UserType.Merchant ? mockTokenMerchant : mockTokenClient;

export const mockQrCode = "QrCode";

export const mockUser: IUser = {
    id: 79,
    email: "usertest@mail.ru",
    name: "userTest",
    updatedAt: new Date(),
    createdAt: new Date()
}

export const mockWallet: IWallet = {
    id: 40,
    balance: 100,
}

export const mockExpensesTransactions: IExpenseTransaction = {
    transactions: [
        {
            amount: 30,
            nameTo: "Starbucks",
        },
        {
            amount: 20,
            nameTo: "McDonald's",
        },
        {
            amount: 20,
            nameTo: "Steam",
        },
        {
            amount: 70,
            nameTo: "Others"
        },
    ],
    amount: 140
}

export const mockIncome: IIncome = {
    amount: 30
}

export const mockTransactionsData: ITransactionData = {
    expensesTransactions: mockExpensesTransactions,
    income: mockIncome
}