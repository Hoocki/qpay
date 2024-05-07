
export const getEndOfCurrentDate = () =>  {
    const currentDate = new Date();
    currentDate.setHours(23,59,59,999);
    return currentDate;
}

export const getStartOfTheYearDate = () => new Date(new Date().getFullYear(), 0, 1, 0, 0, 0, 0);