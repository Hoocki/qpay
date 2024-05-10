import React from 'react';
import {PaymentInformationProps} from "./props";
import {InputAdornment, TextField} from "@mui/material";

const PaymentInformation: React.FC<PaymentInformationProps> = ({data, prefix}) => {
    return (
        <TextField
            value={data}
            disabled={true}
            placeholder="PaymentInformation"
            variant="outlined"
            fullWidth
            margin="normal"
            InputProps={{
                startAdornment: <InputAdornment position="start">{prefix}</InputAdornment>,
            }}
        />
    );
};

export default PaymentInformation;