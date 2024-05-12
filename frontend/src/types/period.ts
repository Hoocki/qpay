export enum PeriodType {
    START = "START",
    END = "END"
}

export interface IPeriod {
    type: PeriodType,
    day: number,
    month: number,
    year: number
}

export enum DateType {
    DAYS = "DAYS",
    MONTHS = "MONTHS",
    YEARS = "YEARS"
}