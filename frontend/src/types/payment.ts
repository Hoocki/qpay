export interface IPayment {
    emailFrom: string,
    walletIdFrom: number,
    emailTo: string,
    walletIdTo: number,
    amount: number,
    sendNotification: boolean
}