import React, {useEffect, useState} from 'react';
import {ValidationErrorMessages} from "../../../../../common/constansts/validationErrorMessages";
import {TextField} from "@mui/material";
import {EmailFieldProps} from "./props";
import {EMAIL_REGEX} from "../../../../../common/constansts/validation";

const EmailField: React.FC<EmailFieldProps> = ({defaultEmail, updateEmailFields}) => {
    const [email, setEmail] = useState<string>(defaultEmail);
    const [isEmailValid, setIsEmailValid] = useState<boolean>(false);

    const handleEmailChange = (email: string) => {
        setEmail(email);
        const isValid = validateEmail(email);
        setIsEmailValid(isValid);
        updateEmailFields(email, isValid);
    };

    const validateEmail = (email: string): boolean => email.trim().match(EMAIL_REGEX) !== null;

    useEffect(() => {
        setEmail(defaultEmail);
        validateEmail(defaultEmail);
    }, []);

    return (
        <TextField
            error={!isEmailValid}
            helperText={isEmailValid ? "" : ValidationErrorMessages.EMAIL_NOT_VALID}
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