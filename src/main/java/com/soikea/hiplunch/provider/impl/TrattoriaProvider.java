package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class TrattoriaProvider extends Provider {

    @Override
    protected String processFeed() {

        String feed = ContentUtil.getUrlContents(getMessageUrl());

        String today = StringUtils.upperCase(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.upperCase(StringHelper.getWeekdayName(1));

        String result = FeedCutter.builder(feed)
                .withStartPoints(today, "<ul class=\"restaurant-menu__items-list\">")
                .withEndPoints(tomorrow, "<div class=\"restaurant-menu__button-container\">")
                .withRemovables("\\n", "&nbsp;", "<.+?>", "&euro;", "\\d\\d?\\,\\d\\d?", "\\(.*?\\)", "SALAATTIPÖYTÄ JA PÄIVÄN KEITTO ALKURUOKANA")
                .startProcess()
                .cleanUp()
                .toString().trim();
        result = StringUtils.lowerCase(result);
        result = StringUtils.capitalize(result);
        return result;
    }

    @Override
    public String getId() {
        return "trattoria";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.raflaamo.fi/fi/jyvaskyla/trattoria-aukio-jyvaskyla";
    }

    @Override
    public String getName() {
        return "Trattoria Aukio";
    }

}
