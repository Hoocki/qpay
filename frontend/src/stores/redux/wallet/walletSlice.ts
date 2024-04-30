import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "../store";
import {UserType} from "../../../types/user";
import {IWallet} from "../../../types/wallet";

const initialState: IWallet = {
    id: -1,
    name: "",
    balance: 0,
    userId: -1,
    userType: UserType.Customer
}

export const walletSlice = createSlice({
    name: 'wallet',
    initialState,
    reducers: {
        getWallet: (state, action: PayloadAction<IWallet>) => ({...action.payload}),
        clearWallet: state => ({...initialState})
    }
})

export const {getWallet, clearWallet} = walletSlice.actions;

export const selectWallet = (state: RootState) => state.walletState;

const walletReducer = walletSlice.reducer;

export default walletReducer;