package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;
import org.apache.commons.lang3.StringUtils;

public class NurkkaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.upperCase(StringHelper.getWeekdayName(0) + "na");
        String tomorrow = StringUtils.upperCase(StringHelper.getWeekdayName(1) + "na");

        return FeedCutter.builder(feed)
            .withStartPoints(today, "LOUNAALLA")
            .withEndPoints(tomorrow, "Sis.")
            .withRemovables("\\n", "&nbsp;", "<.+?>", "\\d\\d?\\.\\d\\d?\\.\\:", "\\(.*?\\)")
            .startProcess()
            .cleanUp()
            .toString().trim();
    }

    @Override
    public String getId() {
        return "nurkka";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.lutakonnurkka.fi/lounas/";
    }

    @Override
    public String getName() {
        return "Lutakon Nurkka";
    }
}
