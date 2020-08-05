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
//        today = today.substring(0, today.length()-2);
        String tomorrow = StringUtils.upperCase(StringHelper.getWeekdayName(1));
//        tomorrow = tomorrow.substring(0, tomorrow.length()-2);

        return FeedCutter.builder(feed)
            .withStartPoints(today, "LOUNAS TARJOLLA")
            .withEndPoints(tomorrow, "KOTIIN TILATTAVAT RUOAT").withSpaceables("<br>")
            .withRemovables("\\d€:", "Päivän keitto", "Päivän annos", "Päivän kasvisannos","\\n", "&nbsp;", "<.+?>", "\\d\\d?\\.\\d\\d?\\.\\:?", "\\(.*?\\)")
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
