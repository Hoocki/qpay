import React from 'react';
import {Alert, Snackbar} from "@mui/material";
import {useAppDispatch, useAppSelector} from "../../stores/redux/hooks";
import {selectNotification, showNotification} from "../../stores/redux/notification/notificationSlice";
import {INotification, NotificationType} from "../../types/notification";
import {Time} from "../../common/constansts/time";

export const createNotification = (message: string, type: NotificationType): INotification => ({
    show: true,
    type,
    message
});

const Notification: React.FC = () => {
    const dispatch = useAppDispatch();
    const {show, message, type} = useAppSelector(selectNotification);

    const closeNotification = (event?: React.SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
            return;
        }
        dispatch(showNotification({show: false}));
    };

    return (
        <Snackbar open={show}
                  autoHideDuration={Time.NOTIFICATION_DURATION}
                  onClose={closeNotification}
                  anchorOrigin={{vertical: 'bottom', horizontal: 'center'}}
        >
            <Alert variant="filled" severity={type}
                   onClose={closeNotification}>
                {message}
            </Alert>
        </Snackbar>
    );
};

export default Notification;