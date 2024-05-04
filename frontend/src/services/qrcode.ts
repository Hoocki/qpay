import {mockEnabled, mockQrCode} from "./mock";
import {QREndpoints} from "../common/constansts/endpoints";
import httpClient from "./httpClient";
import _ from "lodash";
import {QRCodeCredentials} from "../types/QRCodeCredentials";

export const getQrCodeService = async (qrCode: QRCodeCredentials): Promise<string> => {
    if (mockEnabled) return mockQrCode;
    const response = await httpClient.post<string>(QREndpoints.QRCODE, qrCode);
    return _.get(response, "data");
}