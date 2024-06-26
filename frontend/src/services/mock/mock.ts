import {IUser, UserType} from "../../types/user";
import {IWallet} from "../../types/wallet";
import {IOutcome, ITransaction, ITransactionsOutcome, TransactionType,} from "../../types/transactions";
import {IWalletTopUp} from "../../types/payment";

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
    userId: 79
}

export const mockWalletTopUp: IWalletTopUp = {
    amount: 10,
    email: "usertest@mail.ru",
    sendNotification: false
}

export const mockTransactions: ITransaction[] = [
    {
        id: 1,
        nameTo: "Starbucks",
        amount: 50,
        createdAt: new Date(),
        transactionType: TransactionType.PAYMENT
    },
    {
        id: 2,
        nameTo: "Wallet",
        amount: 25,
        createdAt: new Date(),
        transactionType: TransactionType.TOP_UP
    },
    {
        id: 3,
        nameTo: "Steam",
        amount: 100,
        createdAt: new Date(),
        transactionType: TransactionType.PAYMENT
    },
    {
        id: 4,
        nameTo: "Steam",
        amount: 120,
        createdAt: new Date(),
        transactionType: TransactionType.PAYMENT
    },
    {
        id: 5,
        nameTo: "McDonald's",
        amount: 13,
        createdAt: new Date(),
        transactionType: TransactionType.PAYMENT
    },
]

export const mockExpensesOutcome: IOutcome = {
    total: 140,
    transactionsGroup: [
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
    transactionsGroup: []
}

export const mockTransactionsOutcome: ITransactionsOutcome = {
    expenses: mockExpensesOutcome,
    income: mockIncomeOutcome
}