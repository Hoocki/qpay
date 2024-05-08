import {mockEnabled, mockQrCode} from "./mock/mock";
import {QREndpoints} from "../common/constansts/endpoints";
import httpClient from "./httpClient";
import _ from "lodash";
import {IQRCodeData} from "../types/QRCodeCredentials";

export const getQrCodeService = async (qrCode: IQRCodeData): Promise<string> => {
    if (mockEnabled) return mockQrCode;
    const response = await httpClient.post<string>(QREndpoints.QRCODE, qrCode);
    return _.get(response, "data");
}