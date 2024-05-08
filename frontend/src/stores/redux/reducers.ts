import { combineReducers } from 'redux'
import loggedUserReducer from "./loggedUser/loggedUserSlice";
import notificationReducer from "./notification/notificationSlice";

const rootReducer = combineReducers({
    loggedUserState: loggedUserReducer,
    notificationState: notificationReducer,
})

export default rootReducer;
