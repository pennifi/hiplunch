package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.Provider;

public class ShalimarProvider extends Provider {


    @Override
    protected String processFeed() {
        // hidden divs with several weeks in one source...
        return "Katso sivuilta.";
    }

    @Override
    public String getId() {
        return "shalimar";
    }

    @Override
    protected String getMessageUrl() {
        return "https://ravintolashalimar.fi/";
    }

    @Override
    public String getName() {
        return "Shalimar";
    }
}
