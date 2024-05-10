import React, {useEffect, useState} from 'react';
import {InputHelperMessage} from "../../../common/constansts/inputHelperMessage";
import {TextField} from "@mui/material";
import {EmailFieldProps} from "./props";
import {EMAIL_REGEX} from "../../../common/constansts/validation";

const EmailField: React.FC<EmailFieldProps> = ({defaultEmail, updateEmailFields}) => {
    const [email, setEmail] = useState<string>(defaultEmail);

    const handleEmailChange = (email: string) => {
        setEmail(email);
        const isValid = validateEmail(email);
        updateEmailFields(email, isValid);
    };

    const validateEmail = (email: string): boolean => email.trim().match(EMAIL_REGEX) !== null;

    useEffect(() => {
        setEmail(defaultEmail);
        validateEmail(defaultEmail);
    }, []);

    return (
        <TextField
            helperText={InputHelperMessage.EMAIL_NOT_VALID}
            value={email}
            placeholder="Email"
            variant="outlined"
            onChange={(e) => handleEmailChange(e.target.value)}
            fullWidth
            margin="normal"
        />
    );
};

export default EmailField;