import axios from "axios";
import _ from "lodash";
import {store} from "../stores/redux/store";
import {showNotification} from "../stores/redux/notification/notificationSlice";
import {NotificationType} from "../types/notificationType";
import {createNotification} from "../components/notification/Notification";

const httpClient = axios.create({});

const handleErrors = (error: any) => {
    const customMessage: string = _.get(error, 'response.data.message');
    const message: string = _.isEmpty(customMessage) ? error.message : customMessage;
    const notification = createNotification(message, NotificationType.ERROR);
    store.dispatch(showNotification(notification));
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