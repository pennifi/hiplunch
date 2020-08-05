package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class DeeveriProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.capitalize(StringUtils.lowerCase(StringHelper.getWeekdayName(0)));
        String tomorrow = StringUtils.capitalize(StringUtils.lowerCase(StringHelper.getWeekdayName(1)));

        return FeedCutter.builder(feed)
            .withStartPoints(today, "Lounaslista")
            .withEndPoints(tomorrow, "Aamupala")
            .withRemovables("<.+?>", "\\w*[0-9,\\.]+\\w*", "&euro;")
            .withSpaceables(" \\(\\)")
            .fullProcess().trim();

    }

    @Override
    public String getId() {
        return "deeveri";
    }

    @Override
    protected String getMessageUrl() {
        return "https://deeveri.fi/";
    }

    @Override
    public String getName() {
        return "Deeveri";
    }
}
