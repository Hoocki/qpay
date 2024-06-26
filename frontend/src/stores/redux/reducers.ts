import { combineReducers } from 'redux'
import loggedUserReducer from "./loggedUser/loggedUserSlice";
import notificationReducer from "./notification/notificationSlice";
import walletReducer from "./wallet/walletSlicer";

const rootReducer = combineReducers({
    loggedUserState: loggedUserReducer,
    notificationState: notificationReducer,
    walletState: walletReducer
})

export default rootReducer;
