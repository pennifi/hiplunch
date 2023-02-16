package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class SohwitarProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.upperCase(StringHelper.getWeekdayName(0)); // MAANANTAI
        String tomorrow = StringUtils.upperCase(StringHelper.getWeekdayName(1)); // TIISTAI

        feed = feed.replaceAll("<p style=\"text-align: center;\">tai</p>", " | ");

        feed = FeedCutter.builder(feed)
            .withStartPoints(today, "Viikon lounaslista", "Seniorilounas klo 13-14 9€ eläkeläiskorttia näyttämällä")
            .withEndPoints(tomorrow, "PEJANTAI", "LOUNAS SISÄLTÄÄ", "LÄMPIMÄSTI TERVETULOA!")
            .withSpaceables("\\s\\s+?", "\\(.+?\\)", "\\*")
            .withRemovables("\\n", "&nbsp;", "<.+?>", "\\d+\\.\\d+\\.?")
            .startProcess()
            .cleanUp()
            .toString().trim();

        return feed;
    }

    @Override
    public String getId() {
        return "sohwitar";
    }

    @Override
    protected String getMessageUrl() {
        return "https://sohwitar.fi/#lounasmenu";
    }

    @Override
    public String getName() {
        return "Sohwitar";
    }
}
