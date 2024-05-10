import React from 'react';
import {Button} from "@mui/material";
import {ConfirmationButtonProps} from "./props";

const ConfirmationButton: React.FC<ConfirmationButtonProps> = ({handleClick, buttonName, isDisabled}) => {
    return (
        <Button
            onClick={handleClick}
            variant="contained"
            className="button"
            disabled={isDisabled}
        >
            {buttonName}
        </Button>
    );
};

export default ConfirmationButton;