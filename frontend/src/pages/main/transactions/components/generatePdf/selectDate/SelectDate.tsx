import React from 'react';
import {Box, MenuItem, Select} from "@mui/material";
import TextContent from "../../../../../../components/typography/textContent/TextContent";
import {MONTHS, YEARS} from "../../../../../../common/constansts/time";
import {SelectDateProps} from "./props";
import {DateType} from "../../../../../../types/period";

const SelectDate: React.FC<SelectDateProps> = ({handleDateChange, title, days, period, periodType}) => {
    return (
        <Box className="report-date report-end-date">
            <TextContent text={title}/>
            <Select
                value={period.day}
                onChange={(event) => handleDateChange(event, periodType, DateType.DAYS)}
                className="select-date"
            >
                {days.map((day) => (
                    <MenuItem key={day} value={day}>{day}</MenuItem>
                ))}
            </Select>
            <Select
                value={period.month}
                onChange={(event) => handleDateChange(event, periodType, DateType.MONTHS)}
                className="select-date"
            >
                {MONTHS.map((month, index) => (
                    <MenuItem key={index} value={index}>{month}</MenuItem>
                ))}
            </Select>
            <Select
                value={period.year}
                onChange={(event) => handleDateChange(event, periodType, DateType.YEARS)}
                className="select-date"
            >
                {YEARS.map((year) => (
                    <MenuItem key={year} value={year}>{year}</MenuItem>
                ))}
            </Select>
        </Box>
    );
};

export default SelectDate;