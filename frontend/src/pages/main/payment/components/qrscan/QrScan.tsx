import React, {useEffect} from 'react';
import {useAppDispatch} from "../../../../../stores/redux/hooks";
import {IQRCodeData} from "../../../../../types/QRCodeCredentials";
import {Html5Qrcode} from "html5-qrcode";
import {cameraConfig, cameraIdOrConfig, elementId} from "./utils";
import {QrScanProps} from "./props";
import _ from "lodash";
import {showNotification} from "../../../../../stores/redux/notification/notificationSlice";
import {NotificationType} from "../../../../../types/notification";
import {Notifications} from "../../../../../common/constansts/notifications";
import {Box} from "@mui/material";
import "./styles.css";

const QrScan: React.FC<QrScanProps> = ({updateQrCodeData}) => {

    const dispatch = useAppDispatch();

    const validateQrCodeData = (qrCodeData: IQRCodeData): boolean => {
        try {
            return !(_.isEmpty(qrCodeData) || _.isEmpty(qrCodeData.name) || qrCodeData.money <= 0 || qrCodeData.walletId < 0);
        } catch (err) {
            console.log(err);
            return false;
        }
    }

    const parseQrCode = (decodedText: string): boolean => {
        const qrCodeData: IQRCodeData = JSON.parse(decodedText);
        const isValid = validateQrCodeData(qrCodeData);
        if (!isValid) {
            dispatch(showNotification({show: true, type: NotificationType.ERROR, message: Notifications.SCAN_AGAIN}));
        } else {
            updateQrCodeData(qrCodeData);
        }
        return isValid;
    }


    useEffect(() => {

        const qrScannerStop = async () => {
            try {
                await html5QrCode.stop();
            } catch (err) {
                console.log(err);
            }
        };

        const qrCodeSuccess = async (decodedText: string) => {
            const isValid = parseQrCode(decodedText);
            if (!isValid) return;
            await qrScannerStop();
        };

        const startCamera = async (html5QrCode: Html5Qrcode) => {
            await html5QrCode.start(cameraIdOrConfig, cameraConfig, qrCodeSuccess, () => {});
        }

        const html5QrCode = new Html5Qrcode(elementId);
        startCamera(html5QrCode).then();

        return () => {
            qrScannerStop().then();
        };
    }, []);

    return (
        <Box id={elementId}/>
    );
};

export default QrScan;