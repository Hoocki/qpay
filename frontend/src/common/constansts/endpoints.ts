export enum UserEndpoints {
    CUSTOMER = "/customers",
    MERCHANT = "/merchants",
    SIGN_UP_USER = "/signup"
}

export enum AuthEndpoints {
    AUTH = "/auth",
    SIGN_IN_USER = '/signIn',
}

export enum WalletEndpoints {
    WALLET = "/wallets",
    WALLET_BY_USER = "/users"
}

export enum QREndpoints {
    QRCODE = "/qrcode",
}

export enum PaymentEndpoints {
    PAYMENT = "/payments",
    P2B = "/p2b",
    TOP_UP = "/topUp"
}

export enum TransactionEndpoints {
    TRANSACTION = "/transactions",
    REPORT = "/report",
    CHART = "/chart"
}