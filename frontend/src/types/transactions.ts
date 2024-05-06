export const enum TransactionType {
    PAYMENT = "PAYMENT",
    TOP_UP = "TOP_UP"
}

export interface ITransaction {
    id: number,
    amount: number,
    nameTo: string,
    TransactionType: TransactionType,
    createdAt: Date
}