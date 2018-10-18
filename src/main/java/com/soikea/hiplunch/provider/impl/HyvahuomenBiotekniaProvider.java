package com.soikea.hiplunch.provider.impl;

import com.soikea.hiplunch.provider.HyvahuomenProvider;

public class HyvahuomenBiotekniaProvider extends HyvahuomenProvider {

    @Override
    protected String getHyvahuomenIdentifier() {
        return "lounas-bioteknia";
    }

    @Override
    protected String getStartPointOverride() {
        return "<article class=\"full page-content";
    }

    @Override
    protected String getEndPointOverride() {
        return "<div id=\"comments-block\">";
    }

    @Override
    public String getId() {
        return "hh-bioteknia";
    }

    @Override
    protected String getMessageUrl() {
        return HYVAHUOMEN_BASEURL;
    }

    @Override
    public String getName() {
        return "Hyvä Huomen Bioteknia";
    }
}
