package com.soikea.hiplunch.util;

import java.util.Calendar;
import java.util.Locale;

public class StringHelper {

    public static String getWeekdayName(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_WEEK, offset);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        String day = capitalize(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("fi")));
        if (day.endsWith("na")) {
            day = day.substring(0,day.length() -2);
        }
        return day;
    }

    public static String getWeekdayNameWithSaturday(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_WEEK, offset);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        String day = capitalize(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("fi")));
        if (day.endsWith("na")) {
            day = day.substring(0,day.length() -2);
        }
        return day;
    }

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}