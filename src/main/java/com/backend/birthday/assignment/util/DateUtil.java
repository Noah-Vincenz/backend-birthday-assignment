package com.backend.birthday.assignment.util;

import java.time.LocalDate;

public final class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean sameDayOfYear(LocalDate date1, LocalDate date2) {
        return date1.getDayOfYear() == date2.getDayOfYear();
    }

    // TODO:
    //   use d1.getMonth == d2.getMonth && d1.getDay == d2.getDay
    //   edge case: if d2 == leapYear and d2 == 29.02 and d1 == 28.02 return true
    public static boolean sameBirthday(LocalDate date, LocalDate birthday) {
        if (date.isLeapYear() && birthday.isLeapYear()) {
            return sameDayOfYear(date, birthday);
        } else if (!date.isLeapYear() && !birthday.isLeapYear()) {
            return sameDayOfYear(date, birthday);
        } else { // one of the dates is during leap year, the other is not.
            if (birthday.getDayOfYear() < 60 && date.getDayOfYear() < 60) { // both dates are before 29th Feb
                return sameDayOfYear(date, birthday);
            } // we are after 28th february and one of the dates are during leap year
            else if (birthday.isLeapYear() && birthday.getDayOfYear() == 60) { // birthday is on 29th Feb
                return date.getDayOfYear() == 59; // today is the 28h Feb
            }
            return false;
        }
    }
}
