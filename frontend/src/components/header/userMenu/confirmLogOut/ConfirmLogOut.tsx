import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {Titles} from "../../../../common/constansts/titles";
import {Notifications} from "../../../../common/constansts/notifications";
import {Buttons} from "../../../../common/constansts/buttons";
import {ConfirmLogOutProps} from "./props";
import "./styles.css";

const ConfirmLogOut: React.FC<ConfirmLogOutProps> = ({showDialog, handleLogOut, handleClose}) => {
    return (
        <Dialog
            open={showDialog}
            onClose={handleClose}
        >
            <DialogTitle>
                {Titles.LOG_OUT_DIALOG}
            </DialogTitle>
            <DialogContent>
                <DialogContentText>
                    {Notifications.CONFIRM_LOG_OUT}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button variant="contained" className="cancel-button" onClick={handleClose}>{Buttons.CANCEL}</Button>
                <Button variant="contained" className="confirm-button" onClick={handleLogOut} autoFocus>
                    {Buttons.CONFIRM}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ConfirmLogOut;