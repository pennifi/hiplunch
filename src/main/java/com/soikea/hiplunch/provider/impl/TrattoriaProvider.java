package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class TrattoriaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        String today = StringHelper.getWeekdayName(0) + " " + sdf.format(calendar.getTime());
        calendar.roll(Calendar.DAY_OF_WEEK, 1);
        String tomorrow = StringHelper.getWeekdayName(1) + " " + sdf.format(calendar.getTime());

        feed = StringHelper.stripOneDayFromMenu(feed, today, tomorrow, "</body>");

        String result = "";
        String pattern = "<span.+?name\">\\s+?(.+?)\\s+?<\\/span>";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(feed);
        while (m.find()) {
            result = result + m.group(1).trim() + ". ";
        }

        log.debug(result);
        return result;
    }

    @Override
    public String getId() {
        return "trattoria";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.raflaamo.fi/fi/jyvaskyla/trattoria-aukio#navigation_raflaamolunchmenuportlet_war_raflaamorestaurantportlet";
    }

    @Override
    public String getName() {
        return "Trattoria Aukio";
    }
}
