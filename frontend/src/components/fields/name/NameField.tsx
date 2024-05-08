import React, {useEffect, useState} from 'react';
import {NameFieldProps} from "./props";
import {InputHelperMessage} from "../../../common/constansts/inputHelperMessage";
import {TextField} from "@mui/material";
import {NAME_MIN_SIZE} from "../../../common/constansts/validation";

const NameField: React.FC<NameFieldProps> = ({defaultName, updateNameFields, isDisabled}) => {
    const [name, setName] = useState<string>(defaultName);

    const handleNameChange = (name: string) => {
        setName(name);
        const isValid = validateName(name);
        updateNameFields(name, isValid);
    };

    const validateName = (name: string) => name.trim().length >= NAME_MIN_SIZE;

    useEffect(() => {
        setName(defaultName);
        validateName(defaultName);
    }, []);

    return (
        <TextField
            helperText={InputHelperMessage.NAME_NOT_VALID}
            placeholder="Name"
            variant="outlined"
            value={name}
            disabled={isDisabled}
            onChange={(e) => handleNameChange(e.target.value)}
            fullWidth
            margin="normal"
        />
    );
};

export default NameField;