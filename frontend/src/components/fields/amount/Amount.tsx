import React, {useState} from 'react';
import {AmountProps} from "./props";
import {TextField} from "@mui/material";
import {ValidationErrorMessages} from "../../../common/constansts/validationErrorMessages";

const Amount: React.FC<AmountProps> = ({updateAmountFields}) => {
    const [amount, setAmount] = useState<number>(0);
    const [isAmountValid, setIsAmountValid] = useState<boolean>(false);

    const handleAmountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = parseFloat(e.target.value);
        setAmount(inputValue);
        const isValid = validateAmount(inputValue);
        setIsAmountValid(isValid);
        updateAmountFields(amount, isValid);
    };

    const validateAmount = (amount: number): boolean => amount > 0;

    return (
        <TextField
            error={!isAmountValid}
            helperText={isAmountValid ? "" : ValidationErrorMessages.AMOUNT_NOT_VALID}
            placeholder="Amount"
            variant="outlined"
            type="number"
            onChange={handleAmountChange}
            fullWidth
            margin="normal"
        />
    );
};

export default Amount;