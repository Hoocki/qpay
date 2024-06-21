import React from 'react';
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {Notifications} from "../../../../common/constansts/notifications";
import {ConfirmLogOutProps} from "./props";
import {Content} from "../../../../common/constansts/content";
import CancelButton from "../../../buttons/cancelButton/CancelButton";
import ConfirmationButton from "../../../buttons/confirmationButton/ConfirmationButton";
import "./styles.css"

const ConfirmLogOut: React.FC<ConfirmLogOutProps> = ({showDialog, handleLogOut, handleClose}) => {
    return (
        <Dialog
            open={showDialog}
            onClose={handleClose}
        >
            <DialogTitle className="title">
                {Content.LOG_OUT_DIALOG}
            </DialogTitle>
            <DialogContent>
                <DialogContentText className="content">
                    {Notifications.CONFIRM_LOG_OUT}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <CancelButton handleClick={handleClose} className="cancelButton"/>
                <ConfirmationButton handleClick={handleLogOut} isDisabled={false}/>
            </DialogActions>
        </Dialog>
    );
};

export default ConfirmLogOut;