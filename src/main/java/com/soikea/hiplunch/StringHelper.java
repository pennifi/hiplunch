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

    public static String cutInput(String raw, String startString, String endString) {
        String result;
        int startIndex = raw.indexOf(startString);
        if (startIndex >= raw.length() || startIndex < 0) {
            startIndex = raw.length();
        }
        result = raw.substring(startIndex, raw.length());

        int endIndex = result.indexOf(endString);
        if (endIndex >= result.length() || endIndex < 0) {
            endIndex = result.length();
        }
        return result.substring(0, endIndex);
    }

    public static String stripOneDayFromMenu(String rawMenu, String today, String tomorrow, String forceEnding) {

        return stripOneDayFromMenu(rawMenu, today, tomorrow, forceEnding, "");
    }

    public static String stripOneDayFromMenu(String rawMenu, String today, String tomorrow, String forceEnding, String forceStart) {

        String result = cutInput(rawMenu, forceStart, forceEnding);

        result = cutInput(result, today, tomorrow);

        result = result.replaceAll(today, "");
        result = result.replaceAll(tomorrow, "");

        if ( forceEnding != null && result.indexOf(forceEnding) > 0) {
            result = result.substring(0, result.indexOf(forceEnding));
        }

        return result;
    }
}
