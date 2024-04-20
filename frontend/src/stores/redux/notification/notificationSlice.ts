import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "../store";
import {INotification} from "../../../types/notification";

const initialState: INotification = {
    show: false
}

export const notificationSlice = createSlice({
    name: 'notification',
    initialState,
    reducers: {
        showNotification: (state, action: PayloadAction<INotification>) => ({...state, ...action.payload})
    }
})

export const {showNotification} = notificationSlice.actions;

export const selectNotification = (state: RootState) => state.notificationState;

const notificationReducer = notificationSlice.reducer;

export default notificationReducer;