import axios from "axios";
import _ from "lodash";
import {store} from "../stores/redux/store";

const httpClient = axios.create({});

const handleErrors = (error: any) => {
    const customMessage: string = _.get(error, 'response.data.message');
    const message: string = _.isEmpty(customMessage) ? error.message : customMessage;
    console.log("interceptors: " + message);
}

httpClient.interceptors.response.use(config => config, handleErrors);

httpClient.interceptors.request.use(config => {
    const token = store.getState().loggedUserState?.token;
    if (token) {
        config.headers.Authorization = "Bearer " + token;
    }
    return config;
}, handleErrors);

export default httpClient;