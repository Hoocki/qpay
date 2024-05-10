import {mockEnabled, mockQrCode} from "./mock/mock";
import {QREndpoints} from "../common/constansts/endpoints";
import httpClient from "./httpClient";
import _ from "lodash";
import {IQRCodeData} from "../types/QRCodeCredentials";

export const getQrCodeService = async (qrCode: IQRCodeData): Promise<string> => {
    if (mockEnabled) return mockQrCode;
    const response = await httpClient.post<ArrayBuffer>(QREndpoints.QRCODE, qrCode, {
        responseType: "arraybuffer"
    });

    const data = _.get(response, 'data', new ArrayBuffer(0));
    const base64Image = arrayBufferToBase64(data);
    const contentType = _.get(response, 'headers.content-type', 'image/png');
    return createDataURI(contentType, base64Image);
}

const createDataURI = (contentType: string, base64Data: string): string => {
    return `data:${contentType};base64,${base64Data}`;
};

const arrayBufferToBase64 = (buffer: ArrayBuffer): string => {
    const binary = new Uint8Array(buffer);
    let base64 = '';
    binary.forEach((byte) => {
        base64 += String.fromCharCode(byte);
    });
    return btoa(base64);
};