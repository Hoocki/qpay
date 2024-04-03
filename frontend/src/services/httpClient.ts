import axios from "axios";
import _ from "lodash";


const httpClient = axios.create({});

const handleErrors = (error: any) => {
    const customMessage: string = _.get(error, 'response.data.message');
    const message: string = _.isEmpty(customMessage) ? error.message : customMessage;
    console.log("interceptors: " + message);
}

httpClient.interceptors.response.use(config => config, handleErrors);

export default httpClient;