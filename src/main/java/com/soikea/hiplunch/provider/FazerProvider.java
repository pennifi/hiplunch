package com.soikea.hiplunch.provider;

import com.soikea.hiplunch.ContentUtil;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public abstract class FazerProvider extends Provider {

    protected abstract String getFazerId();

    protected String getRssUrl() {  return "http://www.fazer.fi/api/location/menurss/current?pageId="+getFazerId()+"&language=fi"; }

    public String processFeed() {
        StringBuilder stringBuilder = new StringBuilder();

        log.debug(getRssUrl());

        String feedResults = ContentUtil.getRSSFeedResults(getRssUrl());

        if (feedResults.length() < 10) {
            stringBuilder.append(ContentUtil.ERROR_NOT_AVAILABLE);

        } else {
            stringBuilder.append(feedResults);
        }

        return stripTodayFromFazerFeed(stringBuilder.toString());
    }

    private String stripTodayFromFazerFeed(String s) {
        String today = getWeekdayName(0);
        String tomorrow = getWeekdayName(1);
        log.debug(today + "  " + tomorrow);
        int start = s.indexOf(today);
        int end = s.indexOf(tomorrow);
        String result = s.substring(start, end);
        result = result.replaceAll(today, "");
        result = result.replaceAll(tomorrow, "");
        result = result.replaceAll("<.+?>", "");
        result = result.replaceAll("<br />", "");
        result = result.replaceAll("&nbsp;", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\s\\d+?,\\d+", ": </strong>");
        result = result.replaceAll("\\n", "<strong>");
        result = result.replaceAll("  ", ", ");

        return result;
    }

    private String getWeekdayName(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_WEEK, offset);
        return StringUtils.capitalize(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("fi")));
    }

}
