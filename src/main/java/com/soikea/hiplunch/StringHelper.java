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

    public static String stripOneDayFromMenu(String rawMenu, String today, String tomorrow, String forceEnding) {

        String result;

        int startIndex = rawMenu.indexOf(today);
        if (startIndex >= rawMenu.length() || startIndex < 0) {
            startIndex = rawMenu.length();
        }
        result = rawMenu.substring(startIndex, rawMenu.length());

        int endIndex = result.indexOf(tomorrow);
        if (endIndex >= result.length() || endIndex < 0) {
            endIndex = result.length();
        }
        result = result.substring(0, endIndex);

        result = result.replaceAll(today, "");
        result = result.replaceAll(tomorrow, "");

        if ( forceEnding != null && result.indexOf(forceEnding) > 0) {
            result = result.substring(0, result.indexOf(forceEnding));
        }

        return result;
    }
}
