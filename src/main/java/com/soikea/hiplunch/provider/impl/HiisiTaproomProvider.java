package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class HiisiTaproomProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.capitalize(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.capitalize(StringHelper.getWeekdayName(1));

        feed = FeedCutter.builder(feed)
            .withStartPoints(today, "VKO \\d+")
            .withEndPoints(tomorrow, "à La Carte", "Bottleshop: Aukioloajat")
            .withSpaceables("<strong>.*</strong>", "\\(.*?\\)", "\\*", "\\s\\s+?")
            .withRemovables("\\n", "&nbsp;", "<.+?>", "\\d+?\\.?\\d+\\.?", "V?L G")
            .startProcess()
            .cleanUp()
            .toString().trim();

        return feed;
    }

    @Override
    public String getId() {
        return "hiisi-taproom";
    }

    @Override
    protected String getMessageUrl() {
        return "https://hiisi.beer/taproom/";
    }

    @Override
    public String getName() {
        return "Hiisi Taproom";
    }
}
