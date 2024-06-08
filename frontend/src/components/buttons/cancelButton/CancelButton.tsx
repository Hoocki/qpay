import React from 'react';
import {Button, ThemeProvider} from "@mui/material";
import {ButtonProps} from "../props";
import {Buttons} from "../../../common/constansts/buttons";
import {buttonTheme} from "../../../common/themes/button";

const CancelButton: React.FC<ButtonProps> = ({handleClick, buttonName}) => {
    return (
        <ThemeProvider theme={buttonTheme}>
            <Button
                onClick={handleClick}
                variant="contained"
                color="cancel"
                className="button"
            >
                {buttonName ? buttonName : Buttons.CANCEL}
            </Button>
        </ThemeProvider>
    );
};

export default CancelButton;