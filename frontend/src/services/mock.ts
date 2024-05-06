import {IUser, UserType} from "../types/user";
import {IWallet} from "../types/wallet";
import {
    IExpenseTransaction,
    IIncomeTransaction,
    ITransactionData,
    TransactionType
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
    name: TransactionType.PAYMENT,
    transactions: [
        {
            id: 1,
            amount: 30,
            nameTo: "Starbucks",
            TransactionType: TransactionType.PAYMENT,
            createdAt: new Date()
        },
        {
            id: 2,
            amount: 20,
            nameTo: "McDonald's",
            TransactionType: TransactionType.PAYMENT,
            createdAt: new Date()
        },
        {
            id: 4,
            amount: 30,
            nameTo: "Starbucks",
            TransactionType: TransactionType.PAYMENT,
            createdAt: new Date()
        },
        {
            id: 6,
            amount: 20,
            nameTo: "Steam",
            TransactionType: TransactionType.PAYMENT,
            createdAt: new Date()
        },
        {
            id: 7,
            amount: 70,
            nameTo: "Others",
            TransactionType: TransactionType.PAYMENT,
            createdAt: new Date()
        },
    ],
    amount: 170
}

export const mockIncomeTransactions: IIncomeTransaction = {
    name: TransactionType.TOP_UP,
    amount: 30
}

export const mockTransactionsData: ITransactionData = {
    expensesTransaction: mockExpensesTransactions,
    incomeTransaction: mockIncomeTransactions
}