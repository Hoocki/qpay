import {IUser, UserType} from "../types/user";
import {IWallet} from "../types/wallet";
import {IOutcome, ITransactionsOutcome,} from "../types/transactions";

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

export const mockExpensesOutcome: IOutcome = {
    total: 140,
    transactionGroups: [
        {
            amount: 30,
            destination: "Starbucks",
            share: 21
        },
        {
            amount: 20,
            destination: "McDonald's",
            share: 14
        },
        {
            amount: 20,
            destination: "Steam",
            share: 14
        },
        {
            amount: 70,
            destination: "Others",
            share: 50
        },
    ]
}

export const mockIncomeOutcome: IOutcome = {
    total: 30,
    transactionGroups: []
}

export const mockTransactionsOutcome: ITransactionsOutcome = {
    expenses: mockExpensesOutcome,
    income: mockIncomeOutcome
}