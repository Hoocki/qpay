import React from 'react';
import {Button} from "@mui/material";
import {ButtonProps} from "../props";
import {Buttons} from "../../../common/constansts/buttons";

const CancelButton: React.FC<ButtonProps> = ({handleClick, buttonName}) => {
    return (
        <Button
            onClick={handleClick}
            variant="contained"
            className="button"
            color="secondary"
        >
            {buttonName ? buttonName : Buttons.CANCEL}
        </Button>
    );
};

export default CancelButton;