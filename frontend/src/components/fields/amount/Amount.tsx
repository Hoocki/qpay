import React from 'react';
import {AmountProps} from "./props";
import {TextField} from "@mui/material";
import {ValidationErrorMessages} from "../../../common/constansts/validationErrorMessages";
import {LOWER_AMOUNT_LIMIT, UPPER_AMOUNT_LIMIT} from "../../../common/constansts/validation";

const Amount: React.FC<AmountProps> = ({updateAmountField, updateIsValidAmountField}) => {

    const handleAmountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = parseFloat(e.target.value);
        const isValid = validateAmount(inputValue);
        updateIsValidAmountField(isValid);
        if (!isValid) return;
        updateAmountField(inputValue);
    };

    const validateAmount = (amount: number): boolean => amount > LOWER_AMOUNT_LIMIT && amount <= UPPER_AMOUNT_LIMIT;

    return (
        <TextField
            helperText={ValidationErrorMessages.AMOUNT_NOT_VALID}
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