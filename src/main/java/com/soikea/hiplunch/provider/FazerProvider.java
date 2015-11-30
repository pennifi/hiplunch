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
        String result;

        int start = s.indexOf(today);
        if (start >= s.length() || start < 0) { start = s.length(); }
        result = s.substring(start, s.length());

        int end = result.indexOf(tomorrow);
        if (end >= result.length() || end < 0) { end = result.length(); }
        result = result.substring(0, end);

        result = result.replaceAll(today, "");
        result = result.replaceAll(tomorrow, "");
        result = result.replaceAll("<.+?>", "");
        result = result.replaceAll("Lounas 7,10 opiskelijakortilla 2.60Kela tukee korkeakouluopiskelijoiden ruokailua 1,94 euroa/ ateria.", "");

        result = result.replaceAll("<br />", "");
        result = result.replaceAll("&nbsp;", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\(.+?\\)", "");
        result = result.replaceAll("\\s\\d+?,\\d+", ":</strong> ");
        result = result.replaceAll("\\n", " <strong>");
        result = result.replaceAll("  ", ", ");
        result = result.replaceAll(">,", ">");
        result = result.replaceAll(",\\s?<", " <");

        result = result.replaceAll("<strong>Deli salaattibaari:</strong>", "");
        result = result.replaceAll("Päivän keittolounas", "Keitto");
        result = result.replaceAll("Nordic Buffet", "Buffet");
        result = result.replaceAll("Street gourmet grilli", "Grilli");

        return result;
    }

    private String getWeekdayName(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_WEEK, offset);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        String result = StringUtils.capitalize(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.forLanguageTag("fi")));
        log.debug(result);
        return result;
    }

}
