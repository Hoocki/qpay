import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "../store";
import {ILoggedUser} from "../../../types/loggedUser";
import {UserType} from "../../../types/userType";

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
        signIn: (state, action: PayloadAction<ILoggedUser>) => {
            return {...action.payload}
        },
        logOut: state => {
            return {...initialState}
        }
    }
})

export const {signIn, logOut} = loggedUserSlice.actions;

export const selectLoggedUser = (state: RootState) => state.loggedUserState;

const loggedUserReducer = loggedUserSlice.reducer;

export default loggedUserReducer;