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