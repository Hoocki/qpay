import {NotificationType} from "./notificationType";

export interface INotification {
    show: boolean
    message?: string
    type?: NotificationType
}