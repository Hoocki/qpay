import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "../store";
import {ILoggedUser, UserType} from "../../../types/user";

const initialState: ILoggedUser = {
    id: -1,
    email: "",
    name: "",
    userType: UserType.Customer,
    token: ""
}

export const loggedUserSlice = createSlice({
    name: 'loggedUser',
    initialState,
    reducers: {
        signIn: (state, action: PayloadAction<ILoggedUser>) => ({...action.payload}),
        logOut: state => ({...initialState})
    }
})

export const {signIn, logOut} = loggedUserSlice.actions;

export const selectLoggedUser = (state: RootState) => state.loggedUserState;

const loggedUserReducer = loggedUserSlice.reducer;

export default loggedUserReducer;