import React from 'react';
import {Button} from "@mui/material";
import {ConfirmationButtonProps} from "./props";
import {Buttons} from "../../../common/constansts/buttons";

const ConfirmationButton: React.FC<ConfirmationButtonProps> = ({handleClick, buttonName, isDisabled, className}) => {
    return (
        <Button
            onClick={handleClick}
            variant="contained"
            className={`button ${className}`}
            disabled={isDisabled}
        >
            {buttonName ? buttonName : Buttons.CONFIRM}
        </Button>
    );
};

export default ConfirmationButton;