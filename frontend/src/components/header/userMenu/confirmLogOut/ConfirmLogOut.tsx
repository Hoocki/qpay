import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";
import {Notifications} from "../../../../common/constansts/notifications";
import {Buttons} from "../../../../common/constansts/buttons";
import {ConfirmLogOutProps} from "./props";
import {Content} from "../../../../common/constansts/content";

const ConfirmLogOut: React.FC<ConfirmLogOutProps> = ({showDialog, handleLogOut, handleClose}) => {
    return (
        <Dialog
            open={showDialog}
            onClose={handleClose}
        >
            <DialogTitle>
                {Content.LOG_OUT_DIALOG}
            </DialogTitle>
            <DialogContent>
                <DialogContentText>
                    {Notifications.CONFIRM_LOG_OUT}
                </DialogContentText>
            </DialogContent>
            <DialogActions>
                <Button variant="contained" color="secondary" onClick={handleClose}>{Buttons.CANCEL}</Button>
                <Button variant="contained" onClick={handleLogOut} autoFocus>
                    {Buttons.CONFIRM}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default ConfirmLogOut;