package com.example.leave_application_nmims;
public class HolidayItem {
    private String month;
    private String holidays;

    public HolidayItem(String month, String holidays) {
        this.month = month;
        this.holidays = holidays;
    }

    public String getMonth() {
        return month;
    }

    public String getHolidays() {
        return holidays;
    }
}
