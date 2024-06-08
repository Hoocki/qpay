import React from 'react';
import {Button, ThemeProvider} from "@mui/material";
import {ConfirmationButtonProps} from "./props";
import {Buttons} from "../../../common/constansts/buttons";
import {buttonTheme} from "../../../common/themes/button";

const ConfirmationButton: React.FC<ConfirmationButtonProps> = ({handleClick, buttonName, isDisabled, className}) => {
    return (
        <ThemeProvider theme={buttonTheme}>
            <Button
                onClick={handleClick}
                variant="contained"
                color="blue"
                className={`button ${className}`}
                disabled={isDisabled}
            >
                {buttonName ? buttonName : Buttons.CONFIRM}
            </Button>
        </ThemeProvider>
    );
};

export default ConfirmationButton;