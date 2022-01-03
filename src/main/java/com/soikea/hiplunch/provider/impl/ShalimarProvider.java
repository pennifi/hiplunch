package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class ShalimarProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.capitalize(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.capitalize(StringHelper.getWeekdayName(1));

        feed = FeedCutter.builder(feed)
                .withStartPoints(today, "Lounas viikko \\d+")
                .withEndPoints(tomorrow, "<!-- .entry-content -->")
                .withSpaceables("<strong>.*</strong>", "\\(.*?\\)", "\\*", "€", "\\s\\s+?")
                .withRemovables("\\n", "&nbsp;", "<.+?>", "\\d+?\\.?\\d+\\.?", "V?L G")
                .startProcess()
                .cleanUp()
                .toString().trim();

        return feed;
    }

    @Override
    public String getId() {
        return "shalimar";
    }

    @Override
    protected String getMessageUrl() {
        return "https://ravintolashalimar.fi/matkakeskus/lounas/";
    }

    @Override
    public String getName() {
        return "Shalimar Matkakeskus";
    }
}
