export interface ITransaction {
    amount: number,
    nameTo: string,
}

export interface IExpenseTransaction {
    transactions: ITransaction[],
    amount: number
}

export interface IIncome {
    amount: number
}

export interface ITransactionData {
    expensesTransactions: IExpenseTransaction,
    income: IIncome
}