import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "../store";
import {ILoggedUser, UserType} from "../../../types/user";
import _ from "lodash";

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
        addToken: (state, action: PayloadAction<string>) => {
            state.token = action.payload;
        },
        logOut: state => ({...initialState})
    }
})

export const {signIn, logOut, addToken} = loggedUserSlice.actions;

export const selectLoggedUser = (state: RootState) => state.loggedUserState;

export const selectIsLogged = (state: RootState) => !_.isEmpty(state.loggedUserState.token);

export const selectLoggedUserType = (state: RootState) => state.loggedUserState.userType;

const loggedUserReducer = loggedUserSlice.reducer;

export default loggedUserReducer;