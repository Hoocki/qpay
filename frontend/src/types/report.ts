import {UserType} from "./user";

export interface IReport {
    userId: number
    periodStart: Date,
    periodEnd: Date,
    userType: UserType
}