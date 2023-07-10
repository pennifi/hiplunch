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

        String today = StringUtils.capitalize(StringHelper.getWeekdayName(0));
        String tomorrow = StringUtils.capitalize(StringHelper.getWeekdayName(1));

//        log.debug(feed);

        String result = FeedCutter.builder(feed)
                .withStartPoints(today, "Lounas vko", "\\<main\\>")
                .withEndPoints(tomorrow, "VL = vähälaktoosinen", "Tarjolla koko viikon lounasaikoihin", "\\<article class=\"MenuGroupStyles__AllergensInfo-sc", "\\<\\/main\\>")
                .withRemovables("\\<h3 class\\=\"MenuGroupStyles\\_\\_MenuDescription\\-sc.*\\<\\/h3\\>",
                        "\\<div class\\=\"PortionHeaderStyles\\_\\_InlineWrapper.*?\\<\\/div\\>",
                        "\\n",
                        "Lounas: 11:00-14:00.",
                        "&nbsp;",
                        "<.+?>",
                        "&euro;", "€",
                        "\\d\\d?\\,\\d\\d?",
                        "\\(.*?\\)",
                        "Runsas salaattibuffet ja päivän keitto alkuruokana", "Runsas salaattibuffet ja päivän keitto")
                .withSpaceables("\\t", "\\s\\s*")
                .fullProcess().trim();

        return result;
    }

    @Override
    public String getId() {
        return "trattoria";
    }

    @Override
    protected String getMessageUrl() {
        return "https://www.raflaamo.fi/fi/ravintola/jyvaskyla/trattoria-aukio-jyvaskyla/menu/lounas";
    }

    @Override
    public String getName() {
        return "Trattoria Aukio";
    }

}
