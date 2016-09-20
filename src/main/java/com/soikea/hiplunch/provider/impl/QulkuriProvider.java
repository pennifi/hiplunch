package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class QulkuriProvider extends Provider {

    @Override
    protected String processFeed() {
        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = "<h4>" + StringUtils.capitalize(StringHelper.getWeekdayName(0).substring(0, 2)) + "</h4>"; // Ma
        String tomorrow = "<h4>" + StringUtils.capitalize(StringHelper.getWeekdayName(1).substring(0, 2)) + "</h4>"; // Ti

        feed = FeedCutter.builder(feed)
            .withStartPoints(today, "id=\"lutakko-lounaslista\"")
            .withEndPoints(tomorrow, "L = Laktoositon, G = Gluteeniton")
            .withSpaceables("\\s\\s+?")
            .withRemovables("\\n", "&nbsp;", "<.+?>")
            .startProcess()
            .replace(": ", Arrays.asList("</h5>"))
            .cleanUp()
            .toString();

        return feed;
    }

    @Override
    public String getId() {
        return "qulkuri";
    }

    @Override
    protected String getMessageUrl() {
        return "http://www.qulkuri.fi/#lutakko-lounaslista";
    }

    @Override
    public String getName() {
        return "Le Qulkuri Lutakko";
    }
}
