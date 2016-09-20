package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrattoriaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        String today = StringHelper.getWeekdayName(0) + " " + sdf.format(calendar.getTime());
        calendar.roll(Calendar.DAY_OF_WEEK, 1);
        String tomorrow = StringHelper.getWeekdayName(1) + " " + sdf.format(calendar.getTime());

        feed = FeedCutter.builder(feed)
            .withStartPoints(today)
            .withEndPoints(tomorrow, "Trattoria Aukio", "</body>")
            .fullProcess();

        String result = "";
        String pattern = "<span.+?name\">\\s+?(.+?)\\s+?</span>";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(feed);
        while (m.find()) {
            result = result + m.group(1).trim() + ". ";
        }
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
