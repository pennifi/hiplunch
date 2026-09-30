package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.MenuProvider;
import com.soikea.hiplunch.provider.Provider;
import com.soikea.hiplunch.util.ContentUtil;
import com.soikea.hiplunch.util.FeedCutter;
import com.soikea.hiplunch.util.StringHelper;
import org.apache.commons.lang3.StringUtils;

@MenuProvider
public class ShalimarProvider extends Provider {

    @Override
    protected String processFeed() {

        return "Tarkista menu verkkosivuilta.";
    }

    @Override
    public String getId() {
        return "shalimar";
    }

    @Override
    protected String getMessageUrl() {
        return "https://shalimar.fi/jyvaskyla/matkakeskus/lunchmenu/";
    }

    @Override
    public String getName() {
        return "Shalimar Matkakeskus";
    }
}
