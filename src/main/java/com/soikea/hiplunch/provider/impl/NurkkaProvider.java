package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import com.soikea.hiplunch.provider.Provider;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class NurkkaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.upperCase(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.upperCase(StringHelper.getWeekdayName(1));

        return FeedCutter.builder(feed)
            .withStartPoints(today, "PÄIVÄN LOUNAS")
            .withEndPoints(tomorrow, "Lounaaseen sisältyy", "Sis. päivän annos")
            .withRemovables("\\n", "&nbsp;", "<.+?>", "\\d\\d?\\.\\d\\d?\\.\\:?", "\\(.*?\\)")
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
        return "https://www.lutakonnurkka.fi/lounas/";
    }

    @Override
    public String getName() {
        return "Lutakon Nurkka";
    }
}
