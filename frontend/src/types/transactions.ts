export enum TransactionType {
    PAYMENT = "PAYMENT",
    TOP_UP = "TOP_UP"
}

export interface ITransaction {
    id: number,
    nameTo: string,
    amount: number,
    createdAt: Date
    transactionType: TransactionType
}

export interface IPageData {
    page: number,
    size: number
}

export interface ITransactionGroup {
    amount: number,
    destination: string,
    share: number
}

export interface IOutcome {
    total: number,
    transactionGroups: ITransactionGroup[],
}


export interface ITransactionsOutcome {
    expenses: IOutcome,
    income: IOutcome
}