export interface ITransactionGroup {
    amount: number,
    nameTo: string,
    percentage: number
}

export interface IExpenseData {
    transactions: ITransactionGroup[],
    total: number
}

export interface IIncomeData {
    total: number
}

export interface ITransactionData {
    expenses: IExpenseData,
    income: IIncomeData
}