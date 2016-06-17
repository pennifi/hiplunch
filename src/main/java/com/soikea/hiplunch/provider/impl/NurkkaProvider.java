package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;

import java.util.Arrays;

/**
 * @author Mika Pennanen, Soikea Solutions Oy, 30.11.15.
 */
public class NurkkaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringHelper.getWeekdayName(0).toUpperCase();
        String tomorrow = StringHelper.getWeekdayName(1).toUpperCase();

        feed = FeedCutter.builder(feed)
            .withStartPoints(today)
            .withEndPoints(tomorrow, "NURKAN LOUNASLISTA")
            .withRemovables("\\n", "&nbsp;", "<.+?>")
            .startProcess()
            .replace(": ", Arrays.asList("</h5>"))
            .cleanUp()
            .toString();

        return feed +" tai <a href=\""+getMessageUrl()+"\">Nurkan lounaslista</a>.";
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
