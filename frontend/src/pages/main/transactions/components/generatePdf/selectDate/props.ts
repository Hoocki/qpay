import {SelectChangeEvent} from "@mui/material";
import {DateType, IPeriod, PeriodType} from "../../../../../../types/period";

export interface SelectDateProps {
    handleDateChange: (event: SelectChangeEvent<number>, periodType: PeriodType, dateType: DateType) => void,
    title: string,
    period: IPeriod,
    periodType: PeriodType,
    days: number[]
}