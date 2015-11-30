package com.soikea.hiplunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class StringHelper {
    static final Logger log = LoggerFactory.getLogger(StringHelper.class);

    public static String getWeekdayName(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_WEEK, offset);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        return capitalize(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("fi")));
    }

    public static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static String stripOneDayFromMenu(String rawMenu, boolean maximizeDayNames, String forceEnding) {
        String today = StringHelper.getWeekdayName(0);
        if (maximizeDayNames) { today = today.toUpperCase(); }
        String tomorrow = StringHelper.getWeekdayName(1);
        if (maximizeDayNames) { tomorrow = tomorrow.toUpperCase(); }
        String result;

        int start = rawMenu.indexOf(today);
        if (start >= rawMenu.length() || start < 0) {
            start = rawMenu.length();
        }
        result = rawMenu.substring(start, rawMenu.length());

        int end = result.indexOf(tomorrow);
        if (end >= result.length() || end < 0) {
            end = result.length();
        }
        result = result.substring(0, end);

        result = result.replaceAll(today, "");
        result = result.replaceAll(tomorrow, "");

        if ( forceEnding != null && result.indexOf(forceEnding) > 0) {
            result = result.substring(0, result.indexOf(forceEnding));
        }

        return result;
    }
}
