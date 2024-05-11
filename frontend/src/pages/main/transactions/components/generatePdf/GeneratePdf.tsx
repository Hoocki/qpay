import React, {useEffect, useState} from 'react';
import {Box, Card, CardContent, SelectChangeEvent} from "@mui/material";
import CardTitle from "../../../../../components/typography/cardTitle/CardTitle";
import "./styles.css";
import ConfirmationButton from "../../../../../components/buttons/confirmationButton/ConfirmationButton";
import {Buttons} from "../../../../../common/constansts/buttons";
import {IReport} from "../../../../../types/report";
import {DAYS, Time} from "../../../../../common/constansts/time";
import {UserType} from "../../../../../types/user";
import {DateType, IPeriod, PeriodType} from "../../../../../types/period";
import {useAppDispatch, useAppSelector} from "../../../../../stores/redux/hooks";
import {showNotification} from "../../../../../stores/redux/notification/notificationSlice";
import {NotificationType} from "../../../../../types/notification";
import {Notifications} from "../../../../../common/constansts/notifications";
import {sendReport} from "../../../../../services/report";
import {selectLoggedUser} from "../../../../../stores/redux/loggedUser/loggedUserSlice";
import {Titles} from "../../../../../common/constansts/titles";
import {Content} from "../../../../../common/constansts/content";
import SelectDate from "./selectDate/SelectDate";

const initialReport: IReport = {
    userId: -1,
    periodStart: new Date(Time.CURRENT_YEAR, 0, 1),
    periodEnd: new Date(Time.CURRENT_YEAR, 11, 31),
    userType: UserType.Customer
}

const initialStartPeriod: IPeriod = {
    type: PeriodType.START,
    day: 1,
    month: 0,
    year: Time.CURRENT_YEAR
}

const initialEndPeriod: IPeriod = {
    type: PeriodType.END,
    day: 31,
    month: 11,
    year: Time.CURRENT_YEAR
}

const GeneratePdf = () => {

    const [report, setReport] = useState<IReport>(initialReport);
    const [startDays, setStartDays] = useState<number[]>(DAYS);
    const [endDays, setEndDays] = useState<number[]>(DAYS);
    const [startPeriod, setStartPeriod] = useState<IPeriod>(initialStartPeriod);
    const [endPeriod, setEndPeriod] = useState<IPeriod>(initialEndPeriod);
    const loggedUser = useAppSelector(selectLoggedUser);
    const dispatch = useAppDispatch();

    const updateDays = (selectedMonth: number, selectedYear: number, periodType: PeriodType) => {
        const daysInMonth = new Date(selectedYear || 0, (selectedMonth || 0) + 1, 0).getDate();
        if (periodType === PeriodType.START) {
            setStartDays(Array.from({length: daysInMonth}, (_, i) => i + 1));
        } else {
            setEndDays(Array.from({length: daysInMonth}, (_, i) => i + 1));
        }
    }

    const updateReportUser = () => {
        setReport({...report, userId: loggedUser.id});
    }

    const handleDateChange = (event: SelectChangeEvent<number>, periodType: PeriodType, dateType: DateType) => {
        const date = event.target.value as number;
        let updatedPeriod;
        if (periodType === PeriodType.START) {
            updatedPeriod = { ...startPeriod };
        } else {
            updatedPeriod = { ...endPeriod };
        }

        switch (dateType) {
            case DateType.DAYS:
                updatedPeriod = { ...updatedPeriod, day: date };
                break;
            case DateType.MONTHS:
                updatedPeriod = { ...updatedPeriod, month: date };
                break;
            case DateType.YEARS:
                updatedPeriod = { ...updatedPeriod, year: date };
                break;
            default:
                break;
        }

        if (periodType === PeriodType.START) {
            setStartPeriod(updatedPeriod);
            setReport({
                ...report,
                periodStart: new Date(updatedPeriod.year, updatedPeriod.month, updatedPeriod.day)
            });
        } else {
            setEndPeriod(updatedPeriod);
            setReport({
                ...report,
                periodEnd: new Date(updatedPeriod.year, updatedPeriod.month, updatedPeriod.day)
            });
        }

        if (dateType !== DateType.DAYS) {
            updateDays(updatedPeriod.month, updatedPeriod.year, periodType);
        }
    }

    const handleGenerate = async () => {
        if (report.periodStart.getTime() > report.periodEnd.getTime()) {
            dispatch(showNotification({show: true, type: NotificationType.ERROR, message: Notifications.PERIOD_TIME}));
            return;
        }
        await sendReport(report);
        dispatch(showNotification({
            show: true,
            type: NotificationType.SUCCESS,
            message: Notifications.SEND_GENERATE_PDF
        }));
    }

    useEffect(() => {
        updateReportUser()
    }, []);

    return (
        <Card className="card-background card-payment card-generate-pdf">
            <CardContent className="card-content">
                <Box className="balance-info">
                    <CardTitle title={Titles.GENERATE_PDF}/>
                    <SelectDate handleDateChange={handleDateChange}
                                title={Content.SELECT_FROM} period={startPeriod} periodType={PeriodType.START}
                                days={startDays}/>
                    <SelectDate handleDateChange={handleDateChange}
                                title={Content.SELECT_TO} period={endPeriod} periodType={PeriodType.END}
                                days={endDays}/>
                </Box>
                <ConfirmationButton buttonName={Buttons.GENERATE} isDisabled={false} handleClick={handleGenerate}
                                    className={"button-generate-pdf"}/>
            </CardContent>
        </Card>
    );
};

export default GeneratePdf;