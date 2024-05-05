import {User, UserType} from "../types/user";
import {IWallet} from "../types/wallet";
import {Transaction, TransactionType} from "../types/transactions";

// test configs
export const mockEnabled = true;
export const mockUserType = UserType.Customer as UserType;
//

const mockTokenMerchant = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6Ik1FUkNIQU5UIiwidXNlcklkIjoxNCwiZW1haWwiOiJ1c2VydGVzdDEwQG1haWwucnUiLCJzdWIiOiJ1c2VydGVzdDEwQG1haWwucnUiLCJpYXQiOjE3MTQ3OTUzNzV9.QtmHScrPj-G2XTJvZRcft6vjR2NJ8A360pmculfTc_0";
const mockTokenClient = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6IkNVU1RPTUVSIiwidXNlcklkIjo5MSwiZW1haWwiOiJ1c2VydGVzdDVAbWFpbC5ydSIsInN1YiI6InVzZXJ0ZXN0NUBtYWlsLnJ1IiwiaWF0IjoxNzE0Nzk1MzI3fQ.INChxr6QbqP_xmjZf77O9vTLv3UsmjF6PcmLonL05gM";

export const mockToken = mockUserType === UserType.Merchant ? mockTokenMerchant : mockTokenClient;

export const mockQrCode = "QrCode";

export const mockUser: User = {
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

export const mockTransactions: Transaction[] = [
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
        id: 3,
        amount: 15,
        nameTo: "Wallet",
        TransactionType: TransactionType.TOP_UP,
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
        id: 5,
        amount: 25,
        nameTo: "Wallet",
        TransactionType: TransactionType.TOP_UP,
        createdAt: new Date()
    },
    {
        id: 6,
        amount: 25,
        nameTo: "Steam",
        TransactionType: TransactionType.PAYMENT,
        createdAt: new Date()
    },
    {
        id: 8,
        amount: 25,
        nameTo: "DNS",
        TransactionType: TransactionType.PAYMENT,
        createdAt: new Date()
    },
    {
        id: 9,
        amount: 25,
        nameTo: "b",
        TransactionType: TransactionType.PAYMENT,
        createdAt: new Date()
    },
    {
        id: 10,
        amount: 25,
        nameTo: "d",
        TransactionType: TransactionType.PAYMENT,
        createdAt: new Date()
    }
]