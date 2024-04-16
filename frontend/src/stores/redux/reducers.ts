import { combineReducers } from 'redux'
import loggedUserReducer from "./loggedUser/loggedUserSlice";

const rootReducer = combineReducers({
    loggedUserState: loggedUserReducer
})

export default rootReducer;
