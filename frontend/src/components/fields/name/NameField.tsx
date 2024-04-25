import React, {useEffect, useState} from 'react';
import {NameFieldProps} from "./props";
import {ValidationErrorMessages} from "../../../common/constansts/validationErrorMessages";
import {TextField} from "@mui/material";
import {TEXT_FIELD_VALIDATION} from "../../../common/constansts/validation";

const NameField: React.FC<NameFieldProps> = ({defaultName, updateNameFields,}) => {
    const [name, setName] = useState<string>(defaultName);
    const [isNameValid, setIsNameValid] = useState<boolean>(false);

    const handleNameChange = (name: string) => {
        setName(name);
        const isValid = validateName(name);
        setIsNameValid(isValid);
        updateNameFields(name, isValid);
    };

    const validateName = (name: string) => name.trim().length >= TEXT_FIELD_VALIDATION;

    useEffect(() => {
        setName(defaultName);
        validateName(defaultName);
    }, []);

    return (
        <TextField
            error={!isNameValid}
            helperText={isNameValid ? "" : ValidationErrorMessages.NAME_NOT_VALID}
            placeholder="Name"
            variant="outlined"
            value={name}
            onChange={(e) => handleNameChange(e.target.value)}
            fullWidth
            margin="normal"
        />
    );
};

export default NameField;