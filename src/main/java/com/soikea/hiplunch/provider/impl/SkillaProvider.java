package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SkillaProvider extends Provider {

    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(" dd.MM.");

    @Override
    protected String processFeed() {
        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.upperCase(StringHelper.getWeekdayNameWithSaturday(0)) + dateFormat.format(LocalDate.now().plusDays(0)); // MAANANTAI 07.10.
        String tomorrow = StringUtils.upperCase(StringHelper.getWeekdayNameWithSaturday(1)) + dateFormat.format(LocalDate.now().plusDays(1)); // TIISTAI 08.10.

        feed = FeedCutter.builder(feed)
                .withStartPoints(today, "span class=\"days\"")
                .withEndPoints(tomorrow, "div class=\"wrap\"")
                .withRemovables("\\n", "&nbsp;", "<.+?>")
                .withSpaceables("\\s\\s+?", "<br />")
                .startProcess()
                .cleanUpReverse()
                .toString();

        return feed;
    }

    @Override
    public String getId() {
        return "skilla";
    }

    @Override
    protected String getMessageUrl() {
        return "https://ravintolaskilla.fi/lounas/";
    }

    @Override
    public String getName() {
        return "Skilla";
    }
}
