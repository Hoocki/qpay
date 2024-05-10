import React from 'react';
import {Button} from "@mui/material";
import {CancelButtonProps} from "./props";

const CancelButton: React.FC<CancelButtonProps> = ({handleClick, buttonName}) => {
    return (
        <Button
            onClick={handleClick}
            variant="contained"
            className="button"
            color="secondary"
        >
            {buttonName}
        </Button>
    );
};

export default CancelButton;