import {TransactionEndpoints} from "../common/constansts/endpoints";
import httpClient from "./httpClient";
import _ from "lodash";
import {IReport} from "../types/report";

export const sendReport = async (report: IReport) => {
    const targetPath = `${TransactionEndpoints.TRANSACTION}${TransactionEndpoints.REPORT}`;
    const response = await httpClient.post<void>(targetPath, report);
    return _.get(response, "data");
}