export interface INotification {
    show: boolean
    message?: string
    type?: NotificationType
}

export enum NotificationType {
    SUCCESS = "success",
    ERROR = "error",
    WARNING = "warning",
    INFO = "info"
}