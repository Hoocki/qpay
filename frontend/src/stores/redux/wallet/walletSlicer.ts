import {createSlice, PayloadAction} from '@reduxjs/toolkit'
import {RootState} from "../store";
import {IWallet} from "../../../types/wallet";

const initialState= -1


export const walletSlice = createSlice({
    name: 'wallet',
    initialState,
    reducers: {
        addWalletId: (state, action: PayloadAction<IWallet>) => (
            state = action.payload.id),
        clearWalletId: state => (state = initialState)
    }
})

export const {addWalletId, clearWalletId} = walletSlice.actions;

export const selectWalletId = (state: RootState) => state.walletState;

const walletReducer = walletSlice.reducer;

export default walletReducer;