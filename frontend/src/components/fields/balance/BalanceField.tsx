import React, {useEffect, useState} from 'react';
import {TextField} from "@mui/material";
import {useAppSelector} from "../../../stores/redux/hooks";
import {selectLoggedUser} from "../../../stores/redux/loggedUser/loggedUserSlice";
import {getWalletByUserService} from "../../../services/wallet";
import {InputHelperMessage} from "../../../common/constansts/inputHelperMessage";
import {DISABLE_FIELD} from "../../../common/constansts/fields";

const BalanceField = () => {

    const [balance, setBalance] = useState<number>(0);
    const loggedUser = useAppSelector(selectLoggedUser);

    const getBalance = async () => {
        const receivedWallet = await getWalletByUserService(loggedUser.id, loggedUser.userType);
        setBalance(receivedWallet.balance);
    }

    useEffect(() => {
        getBalance().then();
    }, []);

    return (
        <TextField
            value={balance}
            helperText={InputHelperMessage.BALANCE_NOT_VALID}
            placeholder="BalanceField"
            variant="outlined"
            type="number"
            disabled={DISABLE_FIELD}
            fullWidth
            margin="normal"
        />
    );
};

export default BalanceField;