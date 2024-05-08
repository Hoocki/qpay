import React from 'react';
import {AmountFieldConfirmationProps} from "./props";
import {TextField} from "@mui/material";
import {InputHelperMessage} from "../../../common/constansts/inputHelperMessage";
import {DISABLE_FIELD} from "../../../common/constansts/fields";

const AmountFieldConfirmation: React.FC<AmountFieldConfirmationProps> = ({amount}) => {
    return (
        <TextField
            helperText={InputHelperMessage.AMOUNT_NOT_VALID}
            value={amount}
            disabled={DISABLE_FIELD}
            placeholder="AmountFieldConfirmation"
            variant="outlined"
            type="number"
            fullWidth
            margin="normal"
        />
    );
};

export default AmountFieldConfirmation;