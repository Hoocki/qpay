import React, {useState} from 'react';
import {ValidationErrorMessages} from "../../../common/constansts/validationErrorMessages";
import {TextField} from "@mui/material";
import {PasswordFieldProps} from "./props";
import {PASSWORD_MIN_SIZE} from "../../../common/constansts/validation";

const PasswordField: React.FC<PasswordFieldProps> = ({updatePasswordFields}) => {
    const [password, setPassword] = useState<string>("");
    const [isPasswordValid, setIsPasswordValid] = useState<boolean>(false);

    const handlePasswordChange = (password: string) => {
        setPassword(password);
        const isValid = validatePassword(password);
        setIsPasswordValid(isValid);
        updatePasswordFields(password, isValid);
    };

    const validatePassword = (password: string) => password.trim().length >= PASSWORD_MIN_SIZE;

    return (
        <TextField
            error={!isPasswordValid}
            helperText={isPasswordValid ? "" : ValidationErrorMessages.PASSWORD_NOT_VALID}
            placeholder="Password"
            type="password"
            variant="outlined"
            value={password}
            onChange={(e) => handlePasswordChange(e.target.value)}
            fullWidth
            margin="normal"
        />
    );
};

export default PasswordField;