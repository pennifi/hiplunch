package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.ContentUtil;
import com.soikea.hiplunch.FeedCutter;
import com.soikea.hiplunch.provider.Provider;

public class TrattoriaProvider extends Provider {

    @Override
    protected String processFeed() {

        return FeedCutter.builder(ContentUtil.getUrlContents(getMessageUrl()))
            .withStartPoints("restaurant-menu__items-list\">")
            .withEndPoints("<div class=\"restaurant-menu__button-container")
            .withRemovables("<.+?>", "\\w+[0-9,]+\\w+", "&euro;")
            .fullProcess().trim();
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
