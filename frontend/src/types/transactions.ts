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

export interface IExpenseTransaction {
    name: string,
    transactions: ITransaction[],
    amount: number
}

export interface IIncomeTransaction {
    name: string,
    amount: number
}

export interface ITransactionData {
    expensesTransaction: IExpenseTransaction,
    incomeTransaction: IIncomeTransaction
}