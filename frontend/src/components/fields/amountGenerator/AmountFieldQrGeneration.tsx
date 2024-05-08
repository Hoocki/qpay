import React from 'react';
import {AmountFieldQrGenerationProps} from "./props";
import {TextField} from "@mui/material";
import {InputHelperMessage} from "../../../common/constansts/inputHelperMessage";
import {LOWER_AMOUNT_LIMIT, UPPER_AMOUNT_LIMIT} from "../../../common/constansts/validation";

const AmountFieldQrGeneration: React.FC<AmountFieldQrGenerationProps> = ({updateAmountField, updateIsValidAmountField}) => {

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
            helperText={InputHelperMessage.AMOUNT_NOT_VALID}
            placeholder="AmountFieldGenerator"
            variant="outlined"
            type="number"
            onChange={handleAmountChange}
            fullWidth
            margin="normal"
        />
    );
};

export default AmountFieldQrGeneration;