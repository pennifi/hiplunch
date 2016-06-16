package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.StringHelper;
import com.soikea.hiplunch.provider.Provider;
import org.apache.commons.lang3.StringUtils;

public class QulkuriProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.capitalize(StringHelper.getWeekdayName(0).substring(0, 2));
        String tomorrow = StringUtils.capitalize(StringHelper.getWeekdayName(1).substring(0, 2));

        feed = StringHelper.stripOneDayFromMenu(feed, "<h4>"+today+"</h4>", "<h4>"+tomorrow+"</h4>", "id=\"lutakko-bistrolista\"", "id=\"lutakko-lounaslista\"");

        feed = feed.replaceAll("</h5>", ":");
        feed = feed.replaceAll("<p>", " ");

        feed = feed.replaceAll("\\n", "");
        feed = feed.replaceAll("&nbsp;", "");
        feed = feed.replaceAll("<.+?>", "");
        feed = feed.replaceAll("\\s\\s+?", " ");
        return feed.trim();
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
