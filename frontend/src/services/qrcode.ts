import {mockEnabled, mockQrCode} from "./mock";
import {QRPath} from "../common/constansts/serverPaths";
import httpClient from "./httpClient";
import _ from "lodash";
import {QRCodeCredentials} from "../types/QRCodeCredentials";

export const getQrCodeService = async (qrCode: QRCodeCredentials): Promise<string> => {
    if (mockEnabled) return mockQrCode;
    const basePath = QRPath.QRCODE;
    const response = await httpClient.post<string>(basePath, qrCode);
    return _.get(response, "data");
}