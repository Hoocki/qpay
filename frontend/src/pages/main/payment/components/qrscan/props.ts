import {IQRCodeData} from "../../../../../types/QRCodeCredentials";

export interface QrScanProps {
    updateQrCodeData: (qrCodeDate: IQRCodeData) => void
}