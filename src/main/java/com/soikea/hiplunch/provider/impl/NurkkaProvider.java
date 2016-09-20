package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;

import java.util.Arrays;

public class NurkkaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringHelper.capitalize(StringHelper.getWeekdayName(0));
        String tomorrow = StringHelper.capitalize(StringHelper.getWeekdayName(1));

        return FeedCutter.builder(feed)
            .withStartPoints(today, "Nurkan lounas")
            .withEndPoints(tomorrow, "Päivän lounas ")
            .withRemovables("\\n", "&nbsp;", "<.+?>")
            .startProcess()
            .replace(" ", Arrays.asList("</p>", "</h5>"))
            .cleanUp()
            .toString();
    }

    @Override
    public String getId() {
        return "nurkka";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.lutakonnurkka.fi/fi/";
    }

    @Override
    public String getName() {
        return "Lutakon Nurkka";
    }
}
