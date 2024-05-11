export const Time = {
    NOTIFICATION_DURATION: 4000,
    CURRENT_YEAR: new Date().getFullYear(),
    BEGIN_YEAR: new Date(2020, 0, 1).getFullYear()
}

export const MONTHS = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];

export const YEARS = Array.from({ length: Time.CURRENT_YEAR - Time.BEGIN_YEAR}, (_, i) => Time.CURRENT_YEAR - i);

export const DAYS = Array.from({ length: 31 }, (_, i) => i + 1);