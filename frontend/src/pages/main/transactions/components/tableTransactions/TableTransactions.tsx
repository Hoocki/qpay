import React, {useEffect, useState} from 'react';
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow} from "@mui/material";
import {IPageData, ITransaction, TransactionType} from "../../../../../types/transactions";
import {useAppSelector} from "../../../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../../../stores/redux/loggedUser/loggedUserSlice";
import {getTransactionsForPage} from "../../../../../services/transaction";
import {TableTransactionsProps} from "./props";
import "./styles.css";

const TableContent = {
    NAME: "Name",
    TYPE: "Type",
    AMOUNT: "Amount",
    DATE: "Date"
}

const initialPageData: IPageData = {
    page: 0,
    size: 3
}

const TableTransactions: React.FC<TableTransactionsProps> = ({walletId}) => {

    const [transactions, setTransactions] = useState<ITransaction[]>([]);
    const loggedUser = useAppSelector(selectLoggedUser);
    const [pageTransactions, setPageTransactions] = useState<IPageData>(initialPageData);
    const [isLastPage, setIsLastPage] = useState<boolean>(false);

    const getTransactions = async () => {
        const receivedTransactions = await getTransactionsForPage(walletId, pageTransactions.page, pageTransactions.size, loggedUser.userType);
        if (!receivedTransactions) return;
        setTransactions(receivedTransactions);
        handleLastPage(receivedTransactions);
    }

    const handleLastPage = (receivedTransactions: ITransaction[]) => {
        if (receivedTransactions.length < pageTransactions.size) {
            setIsLastPage(true);
        }
        else {
            setIsLastPage(false);
        }
    }

    const handleChangePage = (event: unknown, page: number) => {
        setPageTransactions({...pageTransactions, page});
    };

    useEffect(() => {
        getTransactions().then();
    }, [pageTransactions, walletId]);


    return (
        <Paper>
            <TableContainer className="card-background" component={Paper}>
                <Table className="table-container" aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>{TableContent.NAME}</TableCell>
                            <TableCell>{TableContent.TYPE}</TableCell>
                            <TableCell>{TableContent.DATE}</TableCell>
                            <TableCell>{TableContent.AMOUNT}</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {transactions
                            .map((transaction) => (
                                <TableRow
                                    key={transaction.id}
                                >
                                    <TableCell>
                                        {transaction.nameTo}
                                    </TableCell>
                                    <TableCell>{transaction.transactionType}</TableCell>
                                    <TableCell>{new Date(transaction.createdAt).toLocaleDateString()}</TableCell>
                                    {transaction.transactionType === TransactionType.PAYMENT ?
                                        <TableCell className="cell-payment-amount">-{transaction.amount}</TableCell>
                                    :
                                        <TableCell className="cell-topup-amount">+{transaction.amount}</TableCell>
                                    }
                                </TableRow>
                            ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <TablePagination
                className="card-background"
                rowsPerPageOptions={[pageTransactions.size]}
                component="div"
                slotProps={{
                    actions: {
                        nextButton: {
                            disabled: isLastPage
                        }
                    }
                }}
                labelDisplayedRows={() => `Current page of transactions: ${pageTransactions.page}`}
                count={-1}
                rowsPerPage={pageTransactions.size}
                page={pageTransactions.page}
                onPageChange={handleChangePage}
            />
        </Paper>
    );
};

export default TableTransactions;