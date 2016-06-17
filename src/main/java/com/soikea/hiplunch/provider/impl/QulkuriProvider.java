package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;
import org.apache.commons.lang3.StringUtils;

public class QulkuriProvider extends Provider {

    @Override
    protected String processFeed() {
        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = "<h4>" + StringUtils.capitalize(StringHelper.getWeekdayName(0).substring(0, 2)) + "</h4>"; // Ma
        String tomorrow = "<h4>" + StringUtils.capitalize(StringHelper.getWeekdayName(1).substring(0, 2)) + "</h4>"; // Ti

        feed = StringHelper.stripOneDayFromMenu(feed, today, tomorrow, "L = Laktoositon, G = Gluteeniton", "id=\"lutakko-lounaslista\"");

        feed = feed.replaceAll("</h5>", ":")
            .replaceAll("<p>", " ")
            .replaceAll("\\n", "")
            .replaceAll("&nbsp;", "")
            .replaceAll("<.+?>", "")
            .replaceAll("\\s\\s+?", " ")
            .trim();

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
